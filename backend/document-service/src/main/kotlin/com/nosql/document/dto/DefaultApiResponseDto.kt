package com.nosql.document.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class DefaultApiResponseDto(

    @JsonProperty("message")
    val message: String,
)
