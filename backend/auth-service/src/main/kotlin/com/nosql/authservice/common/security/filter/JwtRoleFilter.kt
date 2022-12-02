package com.nosql.authservice.common.security.filter

import com.nosql.authservice.common.security.authentication.JwtAuthentication
import com.nosql.authservice.constants.authorization.BEARER_TOKEN_PREFIX
import com.nosql.authservice.constants.authorization.ROLE_CLAIM
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtParser
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

class JwtRoleFilter(
    private val accessTokenJwtParser: JwtParser,
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
            ?.let { token -> tryToParseJwsClaims(token) }
            ?.takeIf { claims -> claims[ROLE_CLAIM] != null }
            ?.let { claims -> JwtAuthentication(claims) }

    private fun getTokenOrNull(exchange: ServerWebExchange) =
        exchange.request.headers[HttpHeaders.AUTHORIZATION]
            ?.takeIf { it.size == 1 }
            ?.find { it.startsWith(BEARER_TOKEN_PREFIX) }
            ?.removePrefix(BEARER_TOKEN_PREFIX)

    private fun tryToParseJwsClaims(token: String): Claims? =
        try {
            accessTokenJwtParser.parseClaimsJws(token).body
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
