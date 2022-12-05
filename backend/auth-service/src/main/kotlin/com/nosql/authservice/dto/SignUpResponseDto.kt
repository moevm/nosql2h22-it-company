package com.nosql.authservice.dto

import com.fasterxml.jackson.annotation.JsonProperty

class SignUpResponseDto (

    @JsonProperty("id")
    val id: String,
)