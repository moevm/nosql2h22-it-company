package com.nosql.watcher.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotBlank

@Validated
@ConstructorBinding
@ConfigurationProperties("app")
data class AppProperties(

    @field:NotBlank
    val corsAllowedOrigin: String,
)
