package com.nosql.authservice.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class RefreshTokenDto(

    @JsonProperty("refreshToken")
    val refreshToken: String,
)
