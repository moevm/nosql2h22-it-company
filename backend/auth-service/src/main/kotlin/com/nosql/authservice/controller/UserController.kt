package com.nosql.authservice.controller

import com.nosql.authservice.constants.url.PUBLIC_API_V1_USERS_URL_PATH
import com.nosql.authservice.dto.UserDto
import com.nosql.authservice.service.UserService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(PUBLIC_API_V1_USERS_URL_PATH)
class UserController(
    private val userService: UserService,
) {

    @PostMapping(
        path = [SING_UP_URL_PATH],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE],
    )
    suspend fun signUp(
        @Valid @RequestBody userDto: UserDto,
    ) = userService.signUp(userDto)

    @PostMapping(
        path = [SING_IN_URL_PATH],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE],
    )
    suspend fun signIn(
        @Valid @RequestBody userDto: UserDto,
    ) = userService.signIn(userDto)

    companion object {
        const val SING_IN_URL_PATH = "sing-in"
        const val SING_UP_URL_PATH = "sing-up"

    }
}
