package com.nosql.watcher.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Date
import javax.annotation.Nonnegative
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size
import com.nosql.watcher.constants.jackson.DATE_PATTERN

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class WatcherDto (

    @JsonProperty("id")
    var id: String? = null,

    @JsonProperty("date")
    @JsonFormat(pattern = DATE_PATTERN)
    val date: Date,

    @JsonProperty("projectId")
    val projectId: String,

    @JsonProperty("minutesAmount")
    @field:Nonnegative
    var minutesAmount: Long,

    @JsonProperty("comment")
    @field:NotBlank
    @field:Size(max = 512)
    var comment: String
)