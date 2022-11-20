package com.nosql.authservice.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("app")
data class AppProperties(
    val secretKey: String,
)
