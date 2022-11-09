package com.nosql.authservice.converter

import com.nosql.authservice.dto.UserDto
import com.nosql.authservice.entity.UserEntity
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class UserDtoToUserEntityConverter : Converter<UserDto, UserEntity> {

    override fun convert(from: UserDto): UserEntity {
        return UserEntity(
            login = from.login,
            passwordHash = from.passwordHash,
        )
    }
}