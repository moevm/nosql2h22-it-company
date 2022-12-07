package com.nosql.document.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class ImportDocumentDto (

    @JsonProperty("id")
    var id: String?,

    @JsonProperty("type")
    val type: String?,

    @JsonProperty("userId")
    var userId: String?,

    @JsonProperty("orderDate")
    var orderDate: String?,

    @JsonProperty("completeDate")
    var completeDate: String?,

    @JsonProperty("status")
    var status: String?,
)
