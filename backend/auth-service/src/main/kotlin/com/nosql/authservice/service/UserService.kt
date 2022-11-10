package com.nosql.authservice.service

import com.nosql.authservice.dto.DefaultApiResponseDto
import com.nosql.authservice.dto.TokensDto
import com.nosql.authservice.dto.UserDto

interface UserService {

    suspend fun signUp(userDto: UserDto): DefaultApiResponseDto

    suspend fun signIn(userDto: UserDto): TokensDto
}
