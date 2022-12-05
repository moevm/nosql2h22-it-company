package com.nosql.personservice.entity

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Field

class ConfidentialEntity(

    @Field("passport_data")
    var passportData: PassportDataEntity,

    @Field("nationality")
    var nationality: String,

    @Field("address")
    var address: String,

    @Field("salary")
    var salary: Int,

    @Field("project_ids")
    var projectIds: List<ObjectId>,
)