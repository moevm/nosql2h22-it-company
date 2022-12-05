package com.nosql.document.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.nosql.document.constants.jackson.DATE_PATTERN
import com.nosql.document.enumerator.DocumentStatus
import com.nosql.document.enumerator.DocumentType
import java.util.Date

class ExportDocumentDto(

    @JsonProperty("id")
    var id: String,

    @JsonProperty("type")
    val type: DocumentType,

    @JsonProperty("userId")
    val userId: String,

    @JsonProperty("orderDate")
    @JsonFormat(pattern = DATE_PATTERN)
    val orderDate: Date,

    @JsonProperty("completeDate")
    @JsonFormat(pattern = DATE_PATTERN)
    val completeDate: Date? = null,

    @JsonProperty("status")
    var status: DocumentStatus,
)