package com.nosql.authservice.controller

import com.nosql.authservice.constants.openapi.SECURITY_SCHEME_IDENTIFIER
import com.nosql.authservice.constants.url.PUBLIC_API_V1_AUTH_URL_PATH
import com.nosql.authservice.dto.RefreshTokenDto
import com.nosql.authservice.service.JwtService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(PUBLIC_API_V1_AUTH_URL_PATH)
@SecurityRequirement(name = SECURITY_SCHEME_IDENTIFIER)
class JwtRefreshController(
    private val jwtService: JwtService,
) {

    @PostMapping(
        path = [UPDATE_TOKENS_BY_REFRESH],
        consumes = [APPLICATION_JSON_VALUE],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun updateTokensByRefresh(
        @Valid @RequestBody refreshTokenDto: RefreshTokenDto,
    ) = jwtService.updateTokensByRefresh(refreshTokenDto.refreshToken)

    companion object {

        const val UPDATE_TOKENS_BY_REFRESH = "token/refresh"
    }
}
