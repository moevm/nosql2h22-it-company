package com.nosql.personservice.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.Email

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ContactsDto (

    @JsonProperty("phoneNumber")
    var phoneNumber: String,

    @JsonProperty("email")
    @field:Email
    var email: String,
)
