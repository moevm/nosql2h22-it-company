package com.nosql.document.common.security.filter

import com.nosql.document.configuration.properties.AppProperties
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.web.cors.reactive.CorsUtils
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

class CorsFilter(
    private val appProperties: AppProperties,
) : WebFilter {

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val request: ServerHttpRequest = exchange.request

        if (CorsUtils.isCorsRequest(request)) {
            val response = exchange.response

            response.headers.apply {
                add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, appProperties.corsAllowedOrigin)
                add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, ANY)
                add(HttpHeaders.ACCESS_CONTROL_MAX_AGE, MAX_AGE)
                add(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, ANY)
            }

            if (request.method === HttpMethod.OPTIONS) {
                response.statusCode = HttpStatus.OK
                return Mono.empty()
            }
        }
        return chain.filter(exchange)
    }


    companion object {

        private const val MAX_AGE = "1800"
        private const val ANY = "*"
    }
}
