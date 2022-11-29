package com.nosql.document.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.nosql.document.enumerator.DocumentStatus
import com.nosql.document.enumerator.DocumentType
import java.time.Instant
import java.util.Date

class DocumentDto (

    @JsonProperty("id")
    var id: String? = null,

    @JsonProperty("type")
    val type: DocumentType,

    @JsonProperty("orderDate")
    val orderDate: Date = Date.from(Instant.now()),

    @JsonProperty("status")
    var status: DocumentStatus,
)