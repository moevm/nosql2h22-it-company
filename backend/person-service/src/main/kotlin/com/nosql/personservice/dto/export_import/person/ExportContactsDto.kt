package com.nosql.personservice.dto.export_import.person

import com.fasterxml.jackson.annotation.JsonProperty

class ExportContactsDto(

    @JsonProperty("phoneNumber")
    var phoneNumber: String,

    @JsonProperty("email")
    var email: String,
)
