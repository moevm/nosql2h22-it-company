package com.nosql.personservice.dto

import com.fasterxml.jackson.annotation.JsonProperty

class ConfidentialWithProjectDto(

    @JsonProperty("passportData")
    val passportData: PassportDataDto,

    @JsonProperty("nationality")
    val nationality: String,

    @JsonProperty("address")
    val address: String,

    @JsonProperty("salary")
    val salary: Int,

    @JsonProperty("projectIds")
    var projects: List<ProjectDto>? = null,
)