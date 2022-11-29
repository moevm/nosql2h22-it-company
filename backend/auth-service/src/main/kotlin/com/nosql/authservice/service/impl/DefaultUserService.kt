package com.nosql.authservice.service.impl

import com.nosql.authservice.component.UserComponent
import com.nosql.authservice.dto.DefaultApiResponseDto
import com.nosql.authservice.dto.SignUpRequestDto
import com.nosql.authservice.dto.TokensDto
import com.nosql.authservice.dto.UserDto
import com.nosql.authservice.entity.UserEntity
import com.nosql.authservice.service.JwtService
import com.nosql.authservice.service.UserService
import com.nosql.authservice.util.convert
import org.springframework.core.convert.ConversionService
import org.springframework.stereotype.Service

@Service
class DefaultUserService(
    private val jwtService: JwtService,
    private val userComponent: UserComponent,
    private val conversionService: ConversionService,
) : UserService {

    override suspend fun signUp(signUpRequestDto: SignUpRequestDto): DefaultApiResponseDto {
        conversionService.convert(signUpRequestDto, UserEntity::class)
            .let { userComponent.save(it) }

        return DefaultApiResponseDto("ok")
    }

    override suspend fun signIn(userDto: UserDto): TokensDto {
        val user = conversionService.convert(userDto, UserEntity::class)
            .let { userComponent.getByLoginAndPasswordHash(it) }
        val userId = user.id!!.toHexString()
        val role = user.role!!

        return jwtService.generateTokens(userId, role)
    }
}
