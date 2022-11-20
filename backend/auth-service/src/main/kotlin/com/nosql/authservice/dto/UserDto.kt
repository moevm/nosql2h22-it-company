package com.nosql.authservice.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class UserDto (

    @JsonProperty("login")
    @field:NotBlank(message = "login must not be null or blank")
    @field:Size(min = 1, max = 15, message = "login size must be from 1 to 15")
    @field:Pattern(regexp = "^[A-Za-z\\d_-]{1,15}\$", message = "login can contain letters, numbers, '_', '-'")
    val login: String,

    @JsonProperty("password")
    @field:NotBlank(message = "password must not be null or blank")
    @field:Size(min = 1, max = 32, message = "password size must be from 1 to 32")
    @field:Pattern(
        regexp = "^[A-Za-z\\d@*_-]{1,15}\$",
        message = "password can contain letters, numbers, '_', '-', '@', '*'"
    )
    var password: String
)
