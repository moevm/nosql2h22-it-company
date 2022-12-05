package com.nosql.personservice.dto.export_import.project

import com.fasterxml.jackson.annotation.JsonProperty
import org.bson.types.ObjectId

class ExportProjectDto(

    @JsonProperty("id")
    var id: String,

    @JsonProperty("name")
    val name: String,
)