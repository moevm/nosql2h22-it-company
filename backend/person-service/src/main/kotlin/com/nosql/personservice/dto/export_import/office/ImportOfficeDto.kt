package com.nosql.personservice.dto.export_import.office

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ImportOfficeDto(

    @JsonProperty("id")
    var id: String?,

    @JsonProperty("name")
    val name: String?,

    @JsonProperty("address")
    val address: String?,
)