package com.nosql.authservice.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class DefaultApiResponseDto(

    @JsonProperty("message")
    val message: String,
)
