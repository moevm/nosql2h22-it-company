package com.nosql.authservice.configuration

import com.nosql.authservice.configuration.properties.JwtProperties
import com.nosql.authservice.enumerator.TokenType
import com.nosql.authservice.service.JwksService
import io.jsonwebtoken.JwtParser
import io.jsonwebtoken.Jwts
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JwtConfig(
    private val jwtProperties: JwtProperties,
) {

    @Bean
    fun accessTokenJwtParser(jwksService: JwksService) =
        buildJwtParser(jwksService, TokenType.ACCESS)

    @Bean
    fun refreshTokenJwtParser(jwksService: JwksService) =
        buildJwtParser(jwksService, TokenType.REFRESH)

    private fun buildJwtParser(jwksService: JwksService, tokenType: TokenType): JwtParser =
        Jwts.parserBuilder()
            .requireIssuer(jwtProperties.issuer)
            .setSigningKey(jwksService.getKeyPair(tokenType).private)
            .build()
}
