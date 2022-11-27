package com.nosql.personservice.common.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.web.ReactivePageableHandlerMethodArgumentResolver
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver


@Configuration
internal class WebFluxConfig {

    @Bean
    fun reactivePageableHandlerMethodArgumentResolver(): HandlerMethodArgumentResolver {
        return ReactivePageableHandlerMethodArgumentResolver()
    }
}