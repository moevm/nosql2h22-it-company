package com.nosql.watcher.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class WatcherFillRequestDto (

    @JsonProperty("minutesAmount")
    val minutesAmount: Long,

    @JsonProperty("comment")
    val comment: String
)