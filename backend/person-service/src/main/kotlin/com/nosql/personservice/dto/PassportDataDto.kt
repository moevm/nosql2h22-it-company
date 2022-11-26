package com.nosql.personservice.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Date

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class PassportDataDto (

    @JsonProperty("number")
    var number: String,

    @JsonProperty("issuedPlace")
    var issuedPlace: String,

    @JsonProperty("issuedDate")
    var issuedDate: Date,
)