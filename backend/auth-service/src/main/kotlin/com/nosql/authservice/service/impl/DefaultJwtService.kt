package com.nosql.authservice.service.impl

import com.nosql.authservice.configuration.properties.JwtProperties
import com.nosql.authservice.constants.authorization.ROLE_CLAIM
import com.nosql.authservice.dto.TokensDto
import com.nosql.authservice.enumerator.Role
import com.nosql.authservice.enumerator.TokenType
import com.nosql.authservice.exception.InvalidJwtException
import com.nosql.authservice.service.JwksService
import com.nosql.authservice.service.JwtService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.JwtParser
import io.jsonwebtoken.Jwts
import org.apache.commons.lang3.EnumUtils
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.Instant
import java.util.Date

@Service
class DefaultJwtService(
    private val jwksService: JwksService,
    private val jwtProperties: JwtProperties,
    private val refreshTokenJwtParser: JwtParser,
) : JwtService {

    override fun generateTokens(userId: String, role: Role) =
        TokensDto(
            accessToken = generateAccessToken(userId, role),
            refreshToken = generateRefreshToken(userId, role),
        )

    override fun generateAccessToken(userId: String, role: Role) =
        generateToken(userId, role, jwtProperties.accessToken.ttl, TokenType.ACCESS)

    private fun generateToken(
        userId: String,
        role: Role,
        tokenTtl: Duration,
        tokenType: TokenType,
    ): String {
        val expirationDate = Date.from(Instant.now().plus(tokenTtl))
        val issuedAt = Date.from(Instant.now())

        return Jwts.builder()
            .setIssuer(jwtProperties.issuer)
            .setHeaderParam(KEY_ID_HEADER, jwksService.getKeyId(tokenType))
            .setSubject(userId)
            .setIssuedAt(issuedAt)
            .setExpiration(expirationDate)
            .claim(ROLE_CLAIM, role)
            .signWith(jwksService.getKeyPair(tokenType).private)
            .compact()
    }

    override fun generateRefreshToken(userId: String, role: Role) =
        generateToken(userId, role, jwtProperties.refreshToken.ttl, TokenType.REFRESH)

    override fun generateTokensByRefresh(token: String): TokensDto {
        val claims = parseJwsClaims(token)

        val userId = claims.subject
        val role = getRole(claims)

        return TokensDto(
            accessToken = generateAccessToken(userId, role),
            refreshToken = generateRefreshToken(userId, role),
        )
    }

    private fun parseJwsClaims(token: String): Claims =
        try {
            refreshTokenJwtParser.parseClaimsJws(token).body
        } catch (expectedException: JwtException) {
            throw InvalidJwtException(cause = expectedException)
        }

    private fun getRole(claims: Claims): Role =
        EnumUtils.getEnumIgnoreCase(Role::class.java, claims[ROLE_CLAIM, String::class.java])
            ?: throw InvalidJwtException("Invalid role claim in JWT")

    companion object {

        private const val KEY_ID_HEADER = "kid"
    }
}
