package com.nosql.authservice.service

import com.nosql.authservice.dto.TokensDto

interface JwtService {

    fun generateTokens(userId: String): TokensDto
    fun generateAccessToken(userId: String): String
    fun generateRefreshToken(userId: String): String
    fun updateTokensByRefresh(refreshToken: String): TokensDto
}
