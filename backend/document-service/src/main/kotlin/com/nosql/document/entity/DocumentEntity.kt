package com.nosql.document.entity

import com.nosql.document.enumerator.DocumentStatus
import com.nosql.document.enumerator.DocumentType
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.Date

@Document("document")
class DocumentEntity(

    @Id
    var id: ObjectId? = null,

    @Field("type")
    val type: DocumentType,

    @Field("user_id")
    var userId: ObjectId? = null,

    @Field("order_date")
    val orderDate: Date,

    @Field("complete_date")
    var completeDate: Date? = null,

    @Field("status")
    var status: DocumentStatus,
)