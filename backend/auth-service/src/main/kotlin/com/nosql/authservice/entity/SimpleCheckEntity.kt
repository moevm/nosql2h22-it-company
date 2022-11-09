package com.nosql.authservice.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document("simple_check_document")
data class SimpleCheckEntity(

    @Id
    val id: String? = null,

    @Field("message")
    val message: String,

    @Field("user_name")
    val userName: String,
)