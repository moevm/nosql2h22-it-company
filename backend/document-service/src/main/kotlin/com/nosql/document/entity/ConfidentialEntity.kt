package com.nosql.document.entity

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Field

class ConfidentialEntity(

    @Field("passportData")
    var passportData: PassportDataEntity,

    @Field("nationality")
    var nationality: String,

    @Field("address")
    var address: String,

    @Field("salary")
    var salary: Int,

    @Field("projectIds")
    var projectIds: List<ObjectId>,
)