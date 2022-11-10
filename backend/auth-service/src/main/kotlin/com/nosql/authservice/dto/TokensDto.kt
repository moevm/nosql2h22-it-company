package com.nosql.authservice.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class TokensDto(

    @JsonProperty("accessToken")
    val accessToken: String,

    @JsonProperty("refreshToken")
    val refreshToken: String,
)
