package com.nosql.personservice.dto.export_import.person

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.nosql.personservice.constants.jackson.DATE_PATTERN

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class ImportPassportDataDto(

    @JsonProperty("number")
    var number: String?,

    @JsonProperty("issuedPlace")
    var issuedPlace: String?,

    @JsonProperty("issuedDate")
    @JsonFormat(pattern = DATE_PATTERN)
    var issuedDate: String?,
)
