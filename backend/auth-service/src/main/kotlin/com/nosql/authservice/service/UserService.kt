package com.nosql.authservice.service

import com.nosql.authservice.dto.DefaultApiResponseDto
import com.nosql.authservice.dto.SignInResponseDto
import com.nosql.authservice.dto.UserDto

interface UserService {

    suspend fun signUp(userDto: UserDto): DefaultApiResponseDto

    suspend fun signIn(userDto: UserDto): SignInResponseDto
}
