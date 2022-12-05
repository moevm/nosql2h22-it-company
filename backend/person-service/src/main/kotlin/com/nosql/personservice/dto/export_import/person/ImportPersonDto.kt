package com.nosql.personservice.dto.export_import.person

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class ImportPersonDto (

    @JsonProperty("id")
    var id: String?,

    @JsonProperty("name")
    var name: String?,

    @JsonProperty("surname")
    var surname: String?,

    @JsonProperty("patronymic")
    var patronymic: String?,

    @JsonProperty("sex")
    var sex: String?,

    @JsonProperty("birthday")
    var birthday: String?,

    @JsonProperty("firstWorkDate")
    var firstWorkDate: String?,

    @JsonProperty("position")
    var position: String?,

    @JsonProperty("status")
    var status: String?,

    @JsonProperty("contacts")
    var contacts: ImportContactsDto?,

    @JsonProperty("jobTime")
    var jobTime: ImportJobTimeDto?,

    @JsonProperty("officeId")
    var officeId: String?,

    @JsonProperty("confidential")
    var confidential: ImportConfidentialDto?,

    @JsonProperty("comment")
    var comment: String?,
)