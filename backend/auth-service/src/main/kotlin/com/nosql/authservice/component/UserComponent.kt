package com.nosql.authservice.component

import com.nosql.authservice.entity.UserEntity

interface UserComponent {

    suspend fun save(user: UserEntity): UserEntity

    suspend fun existsByLogin(login: String): Boolean

    suspend fun getByLoginAndPasswordHash(user: UserEntity): UserEntity
}
