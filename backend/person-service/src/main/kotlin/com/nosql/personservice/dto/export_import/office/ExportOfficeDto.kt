package com.nosql.personservice.dto.export_import.office

import com.fasterxml.jackson.annotation.JsonProperty
import org.bson.types.ObjectId

class ExportOfficeDto(

    @JsonProperty("id")
    var id: String,

    @JsonProperty("name")
    val name: String,

    @JsonProperty("address")
    val address: String,
)