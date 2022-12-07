package com.nosql.personservice.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.Pattern

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class JobTimeDto (

    @JsonProperty("start")
    @field:Pattern(regexp = "\\d{2}:\\d{2}")
    var start: String,

    @JsonProperty("end")
    @field:Pattern(regexp = "\\d{2}:\\d{2}")
    var end: String,
)