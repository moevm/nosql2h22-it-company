package com.nosql.authservice.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotBlank

@Validated
@ConstructorBinding
@ConfigurationProperties("jwt")
data class JwtProperties(

    @field:NotBlank
    val issuer: String,

    val accessToken: TokenProperties,

    val refreshToken: TokenProperties,
)
