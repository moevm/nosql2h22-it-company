package com.nosql.personservice.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.nosql.personservice.constants.jackson.DATE_PATTERN
import java.util.Date
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class PassportDataDto (

    @JsonProperty("number")
    @field:NotBlank
    @field:Size(max = 20)
    var number: String,

    @JsonProperty("issuedPlace")
    @field:NotBlank
    @field:Size(max = 100)
    var issuedPlace: String,

    @JsonProperty("issuedDate")
    @JsonFormat(pattern = DATE_PATTERN)
    var issuedDate: Date,
)
