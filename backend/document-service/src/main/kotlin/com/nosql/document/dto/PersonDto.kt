package com.nosql.document.dto

import com.fasterxml.jackson.annotation.JsonProperty

class PersonDto(

    @JsonProperty("name")
    var name: String,

    @JsonProperty("surname")
    var surname: String,

    @JsonProperty("contacts")
    var contacts: ContactsDto,
)