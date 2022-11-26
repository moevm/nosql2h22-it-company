package com.nosql.personservice.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ConfidentialDto (

    @JsonProperty("passportData")
    var passportData: PassportDataDto,

    @JsonProperty("nationality")
    var nationality: String,

    @JsonProperty("address")
    var address: String,

    @JsonProperty("salary")
    var salary: Int,

    @JsonProperty("projectIds")
    var projectIds: List<String>,
)