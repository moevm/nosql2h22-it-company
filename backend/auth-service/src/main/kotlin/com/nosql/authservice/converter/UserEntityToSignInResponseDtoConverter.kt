package com.nosql.authservice.converter

import com.nosql.authservice.dto.SignInResponseDto
import com.nosql.authservice.entity.UserEntity
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class UserEntityToSignInResponseDtoConverter: Converter<UserEntity, SignInResponseDto> {

    override fun convert(from: UserEntity): SignInResponseDto {
        return SignInResponseDto(
            id = from.id!!.toHexString()
        )
    }
}
