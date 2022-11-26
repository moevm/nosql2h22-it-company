package com.nosql.personservice.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class DefaultApiResponseDto(

    @JsonProperty("message")
    val message: String,
)
