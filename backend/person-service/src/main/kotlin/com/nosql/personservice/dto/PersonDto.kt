package com.nosql.personservice.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.nosql.personservice.constants.jackson.DATE_PATTERN
import com.nosql.personservice.enumerator.PositionEnum
import com.nosql.personservice.enumerator.SexEnum
import com.nosql.personservice.enumerator.StatusEnum
import java.util.Date

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class PersonDto (

    @JsonProperty("id")
    var id: String,

    @JsonProperty("name")
    var name: String,

    @JsonProperty("surname")
    var surname: String,

    @JsonProperty("patronymic")
    var patronymic: String?,

    @JsonProperty("sex")
    var sex: SexEnum,

    @JsonProperty("birthday")
    @JsonFormat(pattern = DATE_PATTERN)
    var birthday: Date,

    @JsonProperty("firstWorkDate")
    @JsonFormat(pattern = DATE_PATTERN)
    var firstWorkDate: Date,

    @JsonProperty("position")
    var position: PositionEnum,

    @JsonProperty("status")
    var status: StatusEnum,

    @JsonProperty("contacts")
    var contacts: ContactsDto,

    @JsonProperty("jobTime")
    var jobTime: JobTimeDto,

    @JsonProperty("officeId")
    var officeId: String,

    @JsonProperty("confidential")
    var confidential: ConfidentialDto,

    @JsonProperty("comment")
    var comment: String,
)
