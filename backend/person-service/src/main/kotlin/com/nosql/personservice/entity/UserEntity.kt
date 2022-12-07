package com.nosql.personservice.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "user")
data class UserEntity(

    @Id
    var id: ObjectId,

)