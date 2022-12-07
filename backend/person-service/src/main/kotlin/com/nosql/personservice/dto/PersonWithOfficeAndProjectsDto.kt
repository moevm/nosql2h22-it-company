package com.nosql.personservice.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.nosql.personservice.constants.jackson.DATE_PATTERN
import com.nosql.personservice.enumerator.PositionEnum
import com.nosql.personservice.enumerator.SexEnum
import com.nosql.personservice.enumerator.StatusEnum
import java.util.Date
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class PersonWithOfficeAndProjectsDto(

    @JsonProperty("id")
    val id: String,

    @JsonProperty("name")
    @field:NotBlank
    @field:Size(max = 40)
    val name: String,

    @JsonProperty("surname")
    @field:NotBlank
    @field:Size(max = 40)
    val surname: String,

    @JsonProperty("patronymic")
    @field:NotBlank
    @field:Size(max = 40)
    val patronymic: String?,

    @JsonProperty("sex")
    val sex: SexEnum,

    @JsonProperty("birthday")
    @JsonFormat(pattern = DATE_PATTERN)
    val birthday: Date,

    @JsonProperty("firstWorkDate")
    @JsonFormat(pattern = DATE_PATTERN)
    val firstWorkDate: Date,

    @JsonProperty("position")
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
