package com.nosql.personservice.entity

import org.springframework.data.mongodb.core.mapping.Field

class ContactsEntity(

    @Field("phoneNumber")
    var phoneNumber: String,

    @Field("email")
    var email: String,
)
