package com.nosql.authservice.common.security.configuration

import com.nosql.authservice.enumerator.Role
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.web.server.WebFilter

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class SecurityConfig {

    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity, jwtRoleFilter: WebFilter): SecurityWebFilterChain {
        return http.csrf().disable()
            .httpBasic().disable()
            .formLogin().disable()
            .authorizeExchange {
                it.pathMatchers(ADMIN_URL_PATH_PATTERN).hasAuthority(Role.HR.name)
                    .anyExchange().permitAll()
            }
            .addFilterAt(jwtRoleFilter, SecurityWebFiltersOrder.AUTHENTICATION)
            .build()
    }

    companion object {

        private const val ADMIN_URL_PATH_PATTERN = "/admin/**"
    }
}
