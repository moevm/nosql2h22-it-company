package com.nosql.personservice.dto.export_import.person

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class ImportConfidentialDto(

    @JsonProperty("passportData")
    var passportData: ImportPassportDataDto?,

    @JsonProperty("nationality")
    var nationality: String?,

    @JsonProperty("address")
    var address: String?,

    @JsonProperty("salary")
    var salary: String?,

    @JsonProperty("projectIds")
    var projectIds: List<String?>?,
)
