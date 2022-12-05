package com.nosql.document.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document("person")
class PersonEntity(

    @Id
    var id: ObjectId? = null,

    @Field("name")
    var name: String,

    @Field("surname")
    var surname: String,

    @Field("contacts")
    var contacts: ContactsEntity,
)