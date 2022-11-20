package com.nosql.authservice.service.impl

import com.nosql.authservice.component.UserComponent
import com.nosql.authservice.converter.UserDtoToUserEntityConverter
import com.nosql.authservice.converter.UserEntityToSignInResponseDtoConverter
import com.nosql.authservice.dto.DefaultApiResponseDto
import com.nosql.authservice.dto.SignInResponseDto
import com.nosql.authservice.dto.UserDto
import com.nosql.authservice.service.UserService
import org.springframework.stereotype.Service

@Service
class DefaultUserService(
    private val userDtoToUserEntityConverter: UserDtoToUserEntityConverter,
    private val userEntityToSignInResponseDtoConverter: UserEntityToSignInResponseDtoConverter,
    private val userComponent: UserComponent,
) : UserService {

    override suspend fun signUp(userDto: UserDto): DefaultApiResponseDto {
        val user = userDtoToUserEntityConverter.convert(userDto)
        userComponent.saveIfNotExists(user)
        return DefaultApiResponseDto("ok")
    }

    override suspend fun signIn(userDto: UserDto): SignInResponseDto {
        val user = userComponent.getByLoginAndPasswordHash(userDto)
        return userEntityToSignInResponseDtoConverter.convert(user)
    }
}
