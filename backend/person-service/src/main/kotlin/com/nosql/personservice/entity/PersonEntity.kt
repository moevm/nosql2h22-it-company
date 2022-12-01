package com.nosql.personservice.entity

import com.nosql.personservice.enumerator.PositionEnum
import com.nosql.personservice.enumerator.SexEnum
import com.nosql.personservice.enumerator.StatusEnum
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.Date

@Document(collection = "person")
class PersonEntity(

    @Id
    var id: ObjectId,

    @Field("name")
    var name: String,

    @Field("surname")
    var surname: String,

    @Field("patronymic")
    var patronymic: String?,

    @Field("sex")
    var sex: SexEnum,

    @Field("birthday")
    var birthday: Date,

    @Field("firstWorkDate")
    var firstWorkDate: Date,

    @Field("position")
    var position: PositionEnum,

    @Field("status")
    var status: StatusEnum,

    @Field("contacts")
    var contacts: ContactsEntity,

    @Field("jobTime")
    var jobTime: JobTimeEntity,

    @Field("officeId")
    var officeId: ObjectId,

    @Field("confidential")
    var confidential: ConfidentialEntity,

    @Field("comment")
    var comment: String,
)