package com.nosql.authservice.configuration

import com.nosql.authservice.configuration.properties.JwtProperties
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
    fun jwtParser(jwksService: JwksService): JwtParser {
        return Jwts.parserBuilder()
            .requireIssuer(jwtProperties.issuer)
            .setSigningKey(jwksService.getKeyPair().private)
            .build()
    }
}
