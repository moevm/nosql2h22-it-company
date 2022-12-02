package com.nosql.document.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.nosql.document.constants.jackson.DATE_PATTERN
import com.nosql.document.enumerator.DocumentStatus
import com.nosql.document.enumerator.DocumentStatus.ORDERED
import com.nosql.document.enumerator.DocumentType
import java.time.Instant
import java.util.Date

class DocumentDto (

    @JsonProperty("id")
    var id: String? = null,

    @JsonProperty("type")
    val type: DocumentType,

    @JsonProperty("orderDate")
    @JsonFormat(pattern = DATE_PATTERN)
    val orderDate: Date = Date.from(Instant.now()),

    @JsonProperty("completeDate")
    val completeDate: Date? = null,

    @JsonProperty("status")
    var status: DocumentStatus = ORDERED,
)
