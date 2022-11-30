package com.nosql.personservice.common.auth

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.web.ReactivePageableHandlerMethodArgumentResolver
import org.springframework.web.reactive.config.WebFluxConfigurer
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer

@Configuration
class WebFluxConfig : WebFluxConfigurer {

    @Bean
    fun reactivePageableHandlerMethodArgumentResolver(): HandlerMethodArgumentResolver {
        return ReactivePageableHandlerMethodArgumentResolver()
    }

    override fun configureArgumentResolvers(configurer: ArgumentResolverConfigurer) {
        configurer.addCustomResolver(UserAuthInfoArgumentResolver())
    }
}