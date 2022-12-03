package com.nosql.authservice.entity

import com.nosql.authservice.enumerator.Role
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "user")
data class UserEntity(

    @Id
    var id: ObjectId? = null,

    @Field("login")
    val login: String,

    @Field("password_hash")
    val passwordHash: String,

    @Field("role")
    val role: Role? = null,

    @Field("refresh_token")
    var refreshToken: String? = null,
)
