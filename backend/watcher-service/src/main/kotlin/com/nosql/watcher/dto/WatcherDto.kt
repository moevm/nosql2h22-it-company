package com.nosql.watcher.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Date

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class WatcherDto (

    @JsonProperty("id")
    var id: String? = null,

    @JsonProperty("date")
    val date: Date,

    @JsonProperty("projectId")
    val projectId: String,

    @JsonProperty("minutesAmount")
    var minutesAmount: Long,

    @JsonProperty("comment")
    var comment: String
)