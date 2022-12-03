package com.nosql.authservice.common.auth

import com.nosql.authservice.common.exception.MultipleHttpAuthorizationHeadersException
import com.nosql.authservice.constants.authorization.BEARER_TOKEN_PREFIX
import com.nosql.authservice.exception.InvalidJwtException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtParser
import org.springframework.core.MethodParameter
import org.springframework.http.HttpHeaders
import org.springframework.web.reactive.BindingContext
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

class UserAuthInfoArgumentResolver(
    private val accessTokenJwtParser: JwtParser,
    private val refreshTokenJwtParser: JwtParser,
) : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(UserAuth::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        bindingContext: BindingContext,
        exchange: ServerWebExchange,
    ): Mono<Any> {
        val userAuthAnnotation = parameter.getParameterAnnotation(UserAuth::class.java)
            ?: error("UserToken annotation must be present")

        val authHeaders = exchange.request.headers[HttpHeaders.AUTHORIZATION] ?: emptyList()
        if (authHeaders.size > 1) {
            throw MultipleHttpAuthorizationHeadersException()
        }

        val token = authHeaders.firstOrNull()

        if (token.isNullOrBlank()) {
            if (userAuthAnnotation.required) {
                throw UserAuthNotPresentApiException()
            }
            return Mono.empty()
        }

        return Mono.just(extractToken(token, userAuthAnnotation.refreshToken))
    }

    private fun extractToken(authorizationHeaderValue: String, refreshToken: Boolean): UserAuthInfo {
        val token = authorizationHeaderValue.removePrefix(BEARER_TOKEN_PREFIX)
        val claims = parseClaimsJws(token, refreshToken)

        return UserAuthInfo(claims)
    }

    private fun parseClaimsJws(token: String, refreshToken: Boolean): Claims =
        try {
            resolveJwtParser(refreshToken).parseClaimsJws(token).body
        } catch (expectedException: Exception) {
            throw InvalidJwtException(cause = expectedException)
        }

    private fun resolveJwtParser(refreshToken: Boolean) =
        when (refreshToken) {
            true -> refreshTokenJwtParser
            false -> accessTokenJwtParser
        }
}
