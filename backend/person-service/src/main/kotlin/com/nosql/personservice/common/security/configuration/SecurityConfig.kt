package com.nosql.personservice.common.security.configuration

import com.nosql.personservice.common.security.filter.CorsFilter
import com.nosql.personservice.configuration.properties.AppProperties
import com.nosql.personservice.constants.authorization.ROLE_CLAIM
import com.nosql.personservice.enumerator.Role
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtGrantedAuthoritiesConverterAdapter
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
@EnableWebFluxSecurity
@Suppress("SpreadOperator")
@EnableReactiveMethodSecurity
class SecurityConfig(
    private val appProperties: AppProperties,
) {

    @Bean
    fun securityWebFilterChain(
        http: ServerHttpSecurity,
        jwtAuthenticationConverter: ReactiveJwtAuthenticationConverter,
    ): SecurityWebFilterChain {
        return http.csrf().disable()
            .httpBasic().disable()
            .formLogin().disable()
            .cors().and()
            .authorizeExchange {
                it.pathMatchers(ADMIN_URL_PATH_PATTERN).hasAuthority(Role.HR.name)
                    .pathMatchers(*OPEN_API_PATH_PATTERNS).permitAll()
                    .anyExchange().authenticated()
            }
            .oauth2ResourceServer { oauth2Customizer ->
                oauth2Customizer.jwt { it.jwtAuthenticationConverter(jwtAuthenticationConverter) }
            }
            .addFilterAt(CorsFilter(appProperties), SecurityWebFiltersOrder.CORS)
            .build()
    }

    @Bean
    fun jwtAuthenticationConverter(): ReactiveJwtAuthenticationConverter {
        val jwtConverter = JwtGrantedAuthoritiesConverter()
            .apply {
                setAuthorityPrefix(EMPTY)
                setAuthoritiesClaimName(ROLE_CLAIM)
            }
        val reactiveJwtConverter = ReactiveJwtGrantedAuthoritiesConverterAdapter(jwtConverter)

        return ReactiveJwtAuthenticationConverter()
            .apply { setJwtGrantedAuthoritiesConverter(reactiveJwtConverter) }
    }

    companion object {

        private const val EMPTY = ""
        private const val ADMIN_URL_PATH_PATTERN = "/admin/**"
        private val OPEN_API_PATH_PATTERNS = arrayOf("/v3/api-docs/**", "/webjars/**", "/swagger-ui.html")
    }
}
