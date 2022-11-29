package com.nosql.watcher.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.Date

@Document(collection = "watcher")
class WatcherEntity (

    @Id
    var id: ObjectId? = null,

    @Field("user_id")
    var userId: ObjectId? = null,

    @Field("date")
    val date: Date,

    @Field("project_id")
    val projectId: ObjectId,

    @Field("minutes_amount")
    var minutesAmount: Long,

    @Field("comment")
    var comment: String
)