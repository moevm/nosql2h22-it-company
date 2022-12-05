package com.nosql.personservice.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.nosql.personservice.constants.jackson.DATE_PATTERN
import com.nosql.personservice.enumerator.PositionEnum
import com.nosql.personservice.enumerator.SexEnum
import com.nosql.personservice.enumerator.StatusEnum
import org.springframework.data.mongodb.core.mapping.Field
import java.util.Date

class PersonWithOfficeAndProjectsDto(

    @JsonProperty("id")
    val id: String,

    @JsonProperty("name")
    val name: String,

    @JsonProperty("surname")
    val surname: String,

    @JsonProperty("patronymic")
    val patronymic: String?,

    @JsonProperty("sex")
    val sex: SexEnum,

    @JsonProperty("birthday")
    @JsonFormat(pattern = DATE_PATTERN)
    val birthday: Date,

    @JsonProperty("firstWorkDate")
    @JsonFormat(pattern = DATE_PATTERN)
    val firstWorkDate: Date,

    @Field("position")
    val position: PositionEnum,

    @JsonProperty("status")
    val status: StatusEnum,

    @JsonProperty("contacts")
    val contacts: ContactsDto,

    @JsonProperty("jobTime")
    val jobTime: JobTimeDto,

    @JsonProperty("office")
    var office: OfficeDto? = null,

    @JsonProperty("confidential")
    val confidential: ConfidentialWithProjectDto,

    @JsonProperty("comment")
    val comment: String?,
)
