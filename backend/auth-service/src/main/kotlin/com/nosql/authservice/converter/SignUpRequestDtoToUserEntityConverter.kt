package com.nosql.authservice.converter

import com.nosql.authservice.dto.SignUpRequestDto
import com.nosql.authservice.entity.UserEntity
import com.nosql.authservice.service.PasswordService
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class SignUpRequestDtoToUserEntityConverter(
    private val passwordService: PasswordService,
) : Converter<SignUpRequestDto, UserEntity> {

    override fun convert(from: SignUpRequestDto): UserEntity {
        return UserEntity(
            login = from.login,
            passwordHash = passwordService.convertToHash(from.password),
            role = from.role,
        )
    }
}
