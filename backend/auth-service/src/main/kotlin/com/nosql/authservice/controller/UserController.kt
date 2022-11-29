package com.nosql.authservice.controller

import com.nosql.authservice.constants.url.PUBLIC_API_V1_AUTH_URL_PATH
import com.nosql.authservice.dto.UserDto
import com.nosql.authservice.service.UserService
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(PUBLIC_API_V1_AUTH_URL_PATH)
class UserController(
    private val userService: UserService,
) {

    @PostMapping(
        path = [SIGN_IN_URL_PATH],
        consumes = [APPLICATION_JSON_VALUE],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun signIn(
        @Valid @RequestBody userDto: UserDto,
    ) = userService.signIn(userDto)

    companion object {

        const val SIGN_IN_URL_PATH = "sign-in"
    }
}
