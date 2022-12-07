package com.nosql.watcher.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.nosql.watcher.constants.jackson.DATE_PATTERN
import java.util.Date

class ExportWatcherDto(

    @JsonProperty("id")
    var id: String,

    @JsonProperty("userId")
    var userId: String,

    @JsonProperty("date")
    @JsonFormat(pattern = DATE_PATTERN)
    val date: Date,

    @JsonProperty("projectId")
    val projectId: String,

    @JsonProperty("minutesAmount")
    var minutesAmount: Long,

    @JsonProperty("comment")
    var comment: String,
)