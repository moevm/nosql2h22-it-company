package com.nosql.watcher.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ImportWatcherDto(

    @JsonProperty("id")
    var id: String?,

    @JsonProperty("userId")
    var userId: String?,

    @JsonProperty("date")
    val date: String?,

    @JsonProperty("projectId")
    val projectId: String?,

    @JsonProperty("minutesAmount")
    var minutesAmount: String?,

    @JsonProperty("comment")
    var comment: String?,
)