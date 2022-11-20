package com.nosql.authservice.component

import com.nosql.authservice.dto.UserDto
import com.nosql.authservice.entity.UserEntity

interface UserComponent {

    suspend fun saveIfNotExists(user: UserEntity): UserEntity

    suspend fun existsByLogin(login: String): Boolean

    suspend fun getByLoginAndPasswordHash(userDto: UserDto): UserEntity
}
