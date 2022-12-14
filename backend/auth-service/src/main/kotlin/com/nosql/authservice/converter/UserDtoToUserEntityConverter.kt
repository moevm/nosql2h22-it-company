package com.nosql.authservice.converter

import com.nosql.authservice.dto.UserDto
import com.nosql.authservice.entity.UserEntity
import com.nosql.authservice.service.PasswordService
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class UserDtoToUserEntityConverter(
    private val passwordService: PasswordService,
) : Converter<UserDto, UserEntity> {

    override fun convert(from: UserDto): UserEntity {
        return UserEntity(
            login = from.login,
            passwordHash = passwordService.convertToHash(from.password),
        )
    }
}
