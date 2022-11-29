package com.nosql.authservice.common.security.authentication

import com.nosql.authservice.constants.authorization.ROLE_CLAIM
import io.jsonwebtoken.Claims
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority

class JwtAuthentication(
    private val claims: Claims,
) : Authentication {

    private val authorities: List<SimpleGrantedAuthority>

    init {
        val role = claims[ROLE_CLAIM]
        authorities = listOfNotNull(role)
            .map { SimpleGrantedAuthority(role as String) }
    }

    override fun getName() = null

    override fun getAuthorities() = authorities

    override fun getCredentials() = null

    override fun getDetails() = null

    override fun getPrincipal(): String = claims.subject

    override fun isAuthenticated() = true

    override fun setAuthenticated(isAuthenticated: Boolean) = Unit
}
