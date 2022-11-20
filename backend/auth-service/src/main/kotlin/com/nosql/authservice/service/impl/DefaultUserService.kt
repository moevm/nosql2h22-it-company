package com.nosql.authservice.service.impl

import com.nosql.authservice.component.UserComponent
import com.nosql.authservice.dto.DefaultApiResponseDto
import com.nosql.authservice.dto.SignInResponseDto
import com.nosql.authservice.dto.UserDto
import com.nosql.authservice.entity.UserEntity
import com.nosql.authservice.service.UserService
import com.nosql.authservice.util.convert
import org.springframework.core.convert.ConversionService
import org.springframework.stereotype.Service

@Service
class DefaultUserService(
    private val userComponent: UserComponent,
    private val conversionService: ConversionService,
) : UserService {

    override suspend fun signUp(userDto: UserDto): DefaultApiResponseDto {
        conversionService.convert(userDto, UserEntity::class)
            .let { userComponent.save(it) }

        return DefaultApiResponseDto("ok")
    }

    override suspend fun signIn(userDto: UserDto): SignInResponseDto {
        val user = conversionService.convert(userDto, UserEntity::class)
            .let { userComponent.getByLoginAndPasswordHash(it) }

        return conversionService.convert(user, SignInResponseDto::class)
    }
}
