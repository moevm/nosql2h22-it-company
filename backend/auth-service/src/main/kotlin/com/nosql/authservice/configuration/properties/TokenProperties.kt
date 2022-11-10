package com.nosql.authservice.configuration.properties

import java.time.Duration

data class TokenProperties(

    val ttl: Duration,
)
