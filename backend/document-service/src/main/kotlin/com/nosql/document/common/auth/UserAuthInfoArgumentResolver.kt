package com.nosql.document.common.auth

import com.nimbusds.jwt.SignedJWT
import com.nosql.document.common.exception.InvalidJwtException
import com.nosql.document.common.exception.MultipleHttpAuthorizationHeadersException
import org.springframework.core.MethodParameter
import org.springframework.http.HttpHeaders
import org.springframework.web.reactive.BindingContext
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

class UserAuthInfoArgumentResolver : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(UserAuth::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        bindingContext: BindingContext,
        exchange: ServerWebExchange,
    ): Mono<Any> {
        val userAuthAnnotation = parameter.getParameterAnnotation(UserAuth::class.java)
            ?: throw IllegalStateException("UserToken annotation must be present")
        val isRequiredAuthentication = userAuthAnnotation.required

        val authHeaders = exchange.request.headers[HttpHeaders.AUTHORIZATION] ?: emptyList()
        if (authHeaders.size > 1) {
            throw MultipleHttpAuthorizationHeadersException()
        }
        val token = authHeaders.firstOrNull()

        if (token.isNullOrBlank()) {
            if (isRequiredAuthentication) {
                throw UserAuthNotPresentApiException()
            }
            return Mono.empty()
        }
        return Mono.just(extractToken(token))
    }

    private fun extractToken(token: String): UserAuthInfo {
        val tokenWithoutPrefix = getTokenWithoutBearerPrefix(token)
        val signedJWT = parseJwt(tokenWithoutPrefix)

        return UserAuthInfo(signedJWT)
    }

    private fun parseJwt(tokenWithoutPrefix: String): SignedJWT {
        try {
            return SignedJWT.parse(tokenWithoutPrefix)
        } catch (ex: Exception) {
            throw InvalidJwtException()
        }
    }

    private fun getTokenWithoutBearerPrefix(token: String): String {
        if (token.startsWith(TOKEN_PREFIX)) {
            return token.substring(TOKEN_PREFIX.length)
        }
        return token
    }

    companion object {
        private const val TOKEN_PREFIX = "Bearer "
    }
}
