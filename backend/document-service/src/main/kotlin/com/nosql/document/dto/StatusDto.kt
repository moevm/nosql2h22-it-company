package com.nosql.document.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.nosql.document.enumerator.DocumentStatus

class StatusDto (

    @JsonProperty("status")
    val status: DocumentStatus
)