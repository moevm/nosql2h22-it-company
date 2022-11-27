package com.nosql.authservice.converter

import com.nosql.authservice.configuration.properties.AppProperties
import com.nosql.authservice.dto.UserDto
import com.nosql.authservice.entity.UserEntity
import org.apache.commons.codec.digest.HmacAlgorithms
import org.apache.commons.codec.digest.HmacUtils
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class UserDtoToUserEntityConverter(
    private val appProperties: AppProperties,
) : Converter<UserDto, UserEntity> {

    override fun convert(from: UserDto): UserEntity {
        return UserEntity(
            login = from.login,
            passwordHash = convertPasswordToHash(from.password),
        )
    }

    private fun convertPasswordToHash(password: String) =
        HmacUtils(HmacAlgorithms.HMAC_SHA_256, appProperties.secretKey).hmacHex(password)
}
