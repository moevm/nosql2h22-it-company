package com.nosql.personservice.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import javax.annotation.Nonnegative
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ConfidentialDto (

    @JsonProperty("passportData")
    var passportData: PassportDataDto,

    @JsonProperty("nationality")
    @field:NotBlank
    @field:Size(max = 50)
    var nationality: String,

    @JsonProperty("address")
    @field:NotBlank
    @field:Size(max = 200)
    var address: String,

    @JsonProperty("salary")
    @field:Nonnegative
    var salary: Int,

    @JsonProperty("projectIds")
    var projectIds: List<String>,
)