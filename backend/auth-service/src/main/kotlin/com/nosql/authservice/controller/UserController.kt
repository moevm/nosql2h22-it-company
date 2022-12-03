package com.nosql.authservice.controller

import com.nosql.authservice.common.auth.UserAuth
import com.nosql.authservice.common.auth.UserAuthInfo
import com.nosql.authservice.constants.url.PUBLIC_API_V1_AUTH_URL_PATH
import com.nosql.authservice.dto.UserDto
import com.nosql.authservice.service.UserService
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
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


    @GetMapping(
        path = [SIGN_OUT_URL_PATH],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun signOut(
        @UserAuth userAuthInfo: UserAuthInfo,
    ) = userService.signOut(userAuthInfo.userId)

    @GetMapping(
        path = [UPDATE_TOKENS_BY_REFRESH],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun updateTokensByRefresh(
        @UserAuth(refreshToken = true) userAuthInfo: UserAuthInfo,
        @RequestHeader(AUTHORIZATION) authorizationHeader: String,
    ) = userService.updateTokensByRefresh(userAuthInfo.userId, authorizationHeader)

    companion object {

        const val UPDATE_TOKENS_BY_REFRESH = "token/refresh"
        const val SIGN_OUT_URL_PATH = "sign-out"
        const val SIGN_IN_URL_PATH = "sign-in"
    }
}
