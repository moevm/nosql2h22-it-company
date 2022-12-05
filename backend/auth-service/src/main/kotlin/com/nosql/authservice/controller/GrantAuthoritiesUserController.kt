package com.nosql.authservice.controller

import com.nosql.authservice.constants.openapi.SECURITY_SCHEME_IDENTIFIER
import com.nosql.authservice.constants.url.ADMIN_API_V1_AUTH_URL_PATH
import com.nosql.authservice.dto.SignUpRequestDto
import com.nosql.authservice.service.UserService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(ADMIN_API_V1_AUTH_URL_PATH)
@SecurityRequirement(name = SECURITY_SCHEME_IDENTIFIER)
class GrantAuthoritiesUserController(
    private val userService: UserService,
) {

    @PostMapping(
        path = [SIGN_UP_URL_PATH],
        consumes = [APPLICATION_JSON_VALUE],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun signUp(
        @Valid @RequestBody signUpRequestDto: SignUpRequestDto,
    ) = userService.signUp(signUpRequestDto)

    companion object {

        const val SIGN_UP_URL_PATH = "sign-up"
    }
}
