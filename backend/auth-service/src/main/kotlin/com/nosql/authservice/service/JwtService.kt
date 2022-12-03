package com.nosql.authservice.service

import com.nosql.authservice.dto.TokensDto
import com.nosql.authservice.enumerator.Role

interface JwtService {

    fun generateTokens(userId: String, role: Role): TokensDto

    fun generateAccessToken(userId: String, role: Role): String

    fun generateRefreshToken(userId: String, role: Role): String

    fun generateTokensByRefresh(token: String): TokensDto
}
