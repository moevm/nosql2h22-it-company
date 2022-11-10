package com.nosql.authservice.service.impl

import com.nosql.authservice.configuration.properties.JwtProperties
import com.nosql.authservice.dto.TokensDto
import com.nosql.authservice.exception.InvalidJwtException
import com.nosql.authservice.service.JwksService
import com.nosql.authservice.service.JwtService
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

    override fun generateTokens(userId: String) =
        TokensDto(
            accessToken = generateAccessToken(userId),
            refreshToken = generateRefreshToken(userId),
        )

    override fun generateAccessToken(userId: String) =
        generateToken(userId, jwtProperties.accessToken.ttl)

    private fun generateToken(userId: String, tokenTtl: Duration): String {
        val expirationDate = Date.from(Instant.now().plus(tokenTtl))
        val issuedAt = Date.from(Instant.now())

        return Jwts.builder()
            .setIssuer(jwtProperties.issuer)
            .setHeaderParam(KEY_ID_HEADER, jwksService.getKeyId())
            .setSubject(userId)
            .setIssuedAt(issuedAt)
            .setExpiration(expirationDate)
            .signWith(jwksService.getKeyPair().private)
            .compact()
    }

    override fun generateRefreshToken(userId: String) =
        generateToken(userId, jwtProperties.refreshToken.ttl)

    override fun updateTokensByRefresh(refreshToken: String): TokensDto {
        val userId = parseClaimsJwt(refreshToken).body.subject
        return TokensDto(
            accessToken = generateAccessToken(userId),
            refreshToken = generateRefreshToken(userId),
        )
    }

    private fun parseClaimsJwt(refreshToken: String) =
        try {
            jwtParser.parseClaimsJws(refreshToken)
        } catch (expectedException: JwtException) {
            throw InvalidJwtException(cause = expectedException)
        }

    companion object {

        private const val KEY_ID_HEADER = "kid"
    }
}
