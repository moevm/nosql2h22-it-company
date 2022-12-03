package com.nosql.authservice.common.auth

import io.jsonwebtoken.JwtParser
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.WebFluxConfigurer
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer

@Configuration
class WebFluxConfig(
    private val accessTokenJwtParser: JwtParser,
    private val refreshTokenJwtParser: JwtParser,
) : WebFluxConfigurer {

    override fun configureArgumentResolvers(configurer: ArgumentResolverConfigurer) {
        configurer.addCustomResolver(
            UserAuthInfoArgumentResolver(
                accessTokenJwtParser = accessTokenJwtParser,
                refreshTokenJwtParser = refreshTokenJwtParser,
            )
        )
    }
}
