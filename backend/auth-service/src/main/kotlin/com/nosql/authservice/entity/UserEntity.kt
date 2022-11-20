package com.nosql.authservice.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "user")
data class UserEntity (

    @Id
    var id: ObjectId? = null,

    @Field("login")
    val login: String,

    @Field("password_hash")
    var passwordHash: String
)