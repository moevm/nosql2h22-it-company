package com.nosql.personservice.dto.export_import.person

import com.fasterxml.jackson.annotation.JsonProperty

class ExportConfidentialDto(

    @JsonProperty("passportData")
    var passportData: ExportPassportDataDto,

    @JsonProperty("nationality")
    var nationality: String,

    @JsonProperty("address")
    var address: String,

    @JsonProperty("salary")
    var salary: Int,

    @JsonProperty("projectIds")
    var projectIds: List<String>,
)
