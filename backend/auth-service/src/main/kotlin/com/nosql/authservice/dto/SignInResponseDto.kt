package com.nosql.authservice.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class SignInResponseDto (

    @JsonProperty("id")
    val id: String,
)
