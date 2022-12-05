package com.nosql.personservice.dto.export_import.person

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class ImportJobTimeDto(

    @JsonProperty("start")
    var start: String?,

    @JsonProperty("end")
    var end: String?,
)
