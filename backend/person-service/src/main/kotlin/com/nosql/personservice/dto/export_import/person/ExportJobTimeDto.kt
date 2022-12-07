package com.nosql.personservice.dto.export_import.person

import com.fasterxml.jackson.annotation.JsonProperty

class ExportJobTimeDto(

    @JsonProperty("start")
    var start: String,

    @JsonProperty("end")
    var end: String,
)
