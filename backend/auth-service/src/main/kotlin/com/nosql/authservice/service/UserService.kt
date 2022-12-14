package com.nosql.authservice.service

import com.nosql.authservice.dto.DefaultApiResponseDto
import com.nosql.authservice.dto.SignUpRequestDto
import com.nosql.authservice.dto.SignUpResponseDto
import com.nosql.authservice.dto.TokensDto
import com.nosql.authservice.dto.UserDto

interface UserService {

    suspend fun signUp(signUpRequestDto: SignUpRequestDto): SignUpResponseDto

    suspend fun signIn(userDto: UserDto): TokensDto

    suspend fun signOut(userId: String): DefaultApiResponseDto

    suspend fun updateTokensByRefresh(userId: String, authorizationHeader: String): TokensDto
}
