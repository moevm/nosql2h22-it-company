package com.nosql.authservice.component

import com.nosql.authservice.entity.UserEntity

interface UserComponent {

    suspend fun save(user: UserEntity): UserEntity

    suspend fun existsByLogin(login: String): Boolean

    suspend fun findByLoginAndPasswordHash(user: UserEntity): UserEntity

    suspend fun findByUserId(userId: String): UserEntity

    suspend fun findByUserIdAndRefreshToken(userId: String, refreshToken: String): UserEntity

    suspend fun update(user: UserEntity): UserEntity
}
