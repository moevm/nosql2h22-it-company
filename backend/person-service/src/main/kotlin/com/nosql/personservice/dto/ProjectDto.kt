package com.nosql.personservice.dto

import com.fasterxml.jackson.annotation.JsonProperty

class ProjectDto(

    @JsonProperty("id")
    var id: String,

    @JsonProperty("name")
    var name: String,
)