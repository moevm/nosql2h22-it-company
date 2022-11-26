package com.nosql.personservice.dto

import com.fasterxml.jackson.annotation.JsonProperty

class OfficeDto(

    @JsonProperty("id")
    var id: String,

    @JsonProperty("name")
    var name: String,

    @JsonProperty("address")
    var address: String,
)