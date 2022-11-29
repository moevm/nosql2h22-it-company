package com.nosql.watcher.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document("project")
class ProjectEntity(

    @Id
    var id: ObjectId? = null,

    @Field("name")
    val name: String,
)