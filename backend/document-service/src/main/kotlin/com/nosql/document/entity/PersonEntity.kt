package com.nosql.document.entity

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document("person")
class PersonEntity(
    @Field("name")
    var name: String,

    @Field("surname")
    var surname: String,

    @Field("contacts")
    var contacts: ContactsEntity,
)