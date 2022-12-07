package com.nosql.personservice.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ContactsDto (

    @JsonProperty("phoneNumber")
    @field:NotBlank
    @field:Size(max = 20)
    var phoneNumber: String,

    @JsonProperty("email")
    @field:Email
    var email: String,
)
