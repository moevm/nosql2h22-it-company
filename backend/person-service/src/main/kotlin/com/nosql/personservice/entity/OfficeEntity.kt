package com.nosql.personservice.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document("office")
class OfficeEntity(

    @Id
    var id: ObjectId? = null,

    @Field("name")
    val name: String,

    @Field("address")
    val address: String,
)