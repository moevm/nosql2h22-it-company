package com.nosql.authservice.service.impl

import com.nosql.authservice.configuration.properties.AppProperties
import com.nosql.authservice.service.PasswordService
import org.apache.commons.codec.digest.HmacAlgorithms
import org.apache.commons.codec.digest.HmacUtils
import org.springframework.stereotype.Service

@Service
class DefaultPasswordService(
    private val appProperties: AppProperties,
) : PasswordService {

    override fun convertToHash(password: String): String =
        HmacUtils(HmacAlgorithms.HMAC_SHA_256, appProperties.secretKey).hmacHex(password)
}
