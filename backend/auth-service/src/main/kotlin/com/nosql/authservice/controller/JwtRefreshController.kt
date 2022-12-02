package com.nosql.authservice.controller

import com.nosql.authservice.constants.openapi.SECURITY_SCHEME_IDENTIFIER
import com.nosql.authservice.constants.url.PUBLIC_API_V1_AUTH_URL_PATH
import com.nosql.authservice.service.JwtService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(PUBLIC_API_V1_AUTH_URL_PATH)
@SecurityRequirement(name = SECURITY_SCHEME_IDENTIFIER)
class JwtRefreshController(
    private val jwtService: JwtService,
) {

    @GetMapping(
        path = [UPDATE_TOKENS_BY_REFRESH],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun updateTokensByRefresh(
        @RequestHeader(AUTHORIZATION) authorizationHeader: String,
    ) = jwtService.updateTokensByRefresh(authorizationHeader)

    companion object {

        const val UPDATE_TOKENS_BY_REFRESH = "token/refresh"
    }
}
