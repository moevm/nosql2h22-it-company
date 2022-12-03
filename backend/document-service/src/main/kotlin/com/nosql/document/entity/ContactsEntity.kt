package com.nosql.document.entity

import org.springframework.data.mongodb.core.mapping.Field

class ContactsEntity(

    @Field("phoneNumber")
    var phoneNumber: String,

    @Field("email")
    var email: String,
)
