package com.nosql.personservice.dto.export_import.project

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ImportProjectDto(

    @JsonProperty("id")
    var id: String?,

    @JsonProperty("name")
    var name: String?,
)