package com.nosql.authservice.common.security.filter

import com.nosql.authservice.common.security.authentication.JwtAuthentication
import com.nosql.authservice.constants.authorization.BEARER_TOKEN_PREFIX
import com.nosql.authservice.constants.authorization.ROLE_CLAIM
import com.nosql.authservice.service.JwtService
import io.jsonwebtoken.Claims
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@Component
class JwtRoleFilter(
    private val jwtService: JwtService,
) : WebFilter {

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {

        if (!exchange.request.uri.path.startsWith(ADMIN_URL_PATH_PREFIX)) {
            return chain.filter(exchange)
        }

        val jwtAuthentication = getJwtAuthenticationOrNull(exchange)

        if (jwtAuthentication == null) {
            setUnauthorized(exchange)
            return chain.filter(exchange)
        }

        return chain.filter(exchange)
            .contextWrite(ReactiveSecurityContextHolder.withAuthentication(jwtAuthentication))
    }

    private fun getJwtAuthenticationOrNull(exchange: ServerWebExchange) =
        getTokenOrNull(exchange)
            ?.let { token -> tryToParseJwtClaims(token) }
            ?.takeIf { claims -> claims[ROLE_CLAIM] != null }
            ?.let { claims -> JwtAuthentication(claims) }

    private fun getTokenOrNull(exchange: ServerWebExchange) =
        exchange.request.headers[HttpHeaders.AUTHORIZATION]
            ?.takeIf { it.size == 1 }
            ?.find { it.startsWith(BEARER_TOKEN_PREFIX) }
            ?.removePrefix(BEARER_TOKEN_PREFIX)

    private fun tryToParseJwtClaims(token: String): Claims? =
        try {
            jwtService.parseJwtClaims(token)
        } catch (expectedException: Exception) {
            null
        }

    private fun setUnauthorized(exchange: ServerWebExchange) {
        exchange.response.rawStatusCode = HttpStatus.UNAUTHORIZED.value()
    }

    companion object {

        private const val ADMIN_URL_PATH_PREFIX = "/admin"
    }
}
