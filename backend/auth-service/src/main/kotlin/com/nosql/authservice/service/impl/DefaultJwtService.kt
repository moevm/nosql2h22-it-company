package com.nosql.authservice.service.impl

import com.nosql.authservice.configuration.properties.JwtProperties
import com.nosql.authservice.constants.authorization.BEARER_TOKEN_PREFIX
import com.nosql.authservice.constants.authorization.ROLE_CLAIM
import com.nosql.authservice.dto.TokensDto
import com.nosql.authservice.enumerator.Role
import com.nosql.authservice.exception.InvalidJwtException
import com.nosql.authservice.service.JwksService
import com.nosql.authservice.service.JwtService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.JwtParser
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.Instant
import java.util.Date

@Service
class DefaultJwtService(
    private val jwtProperties: JwtProperties,
    private val jwksService: JwksService,
    private val jwtParser: JwtParser,
) : JwtService {

    override fun generateTokens(userId: String, role: Role) =
        TokensDto(
            accessToken = generateAccessToken(userId, role),
            refreshToken = generateRefreshToken(userId, role),
        )

    override fun generateAccessToken(userId: String, role: Role) =
        generateToken(userId, role, jwtProperties.accessToken.ttl)

    private fun generateToken(userId: String, role: Role, tokenTtl: Duration): String {
        val expirationDate = Date.from(Instant.now().plus(tokenTtl))
        val issuedAt = Date.from(Instant.now())

        return Jwts.builder()
            .setIssuer(jwtProperties.issuer)
            .setHeaderParam(KEY_ID_HEADER, jwksService.getKeyId())
            .setSubject(userId)
            .setIssuedAt(issuedAt)
            .setExpiration(expirationDate)
            .claim(ROLE_CLAIM, role)
            .signWith(jwksService.getKeyPair().private)
            .compact()
    }

    override fun generateRefreshToken(userId: String, role: Role) =
        generateToken(userId, role, jwtProperties.refreshToken.ttl)

    override fun updateTokensByRefresh(authorizationHeader: String): TokensDto {
        val token = authorizationHeader.removePrefix(BEARER_TOKEN_PREFIX)
        val jwtClaims = parseJwtClaims(token)

        val userId = jwtClaims.subject
        val role = jwtClaims[ROLE_CLAIM, Role::class.java]

        return TokensDto(
            accessToken = generateAccessToken(userId, role),
            refreshToken = generateRefreshToken(userId, role),
        )
    }

    override fun parseJwtClaims(token: String): Claims =
        try {
            jwtParser.parseClaimsJws(token).body
        } catch (expectedException: JwtException) {
            throw InvalidJwtException(cause = expectedException)
        }

    companion object {

        private const val KEY_ID_HEADER = "kid"
    }
}
