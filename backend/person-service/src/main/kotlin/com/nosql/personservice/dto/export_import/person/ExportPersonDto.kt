package com.nosql.personservice.dto.export_import.person

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.nosql.personservice.constants.jackson.DATE_PATTERN
import com.nosql.personservice.entity.ConfidentialEntity
import com.nosql.personservice.entity.ContactsEntity
import com.nosql.personservice.entity.JobTimeEntity
import com.nosql.personservice.enumerator.PositionEnum
import com.nosql.personservice.enumerator.SexEnum
import com.nosql.personservice.enumerator.StatusEnum
import org.bson.types.ObjectId
import java.util.Date

class ExportPersonDto(

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
    var contacts: ExportContactsDto,

    @JsonProperty("jobTime")
    var jobTime: ExportJobTimeDto,

    @JsonProperty("officeId")
    var officeId: String,

    @JsonProperty("confidential")
    var confidential: ExportConfidentialDto,

    @JsonProperty("comment")
    var comment: String?,
)