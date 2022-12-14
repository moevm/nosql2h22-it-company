package com.nosql.personservice.entity

import org.springframework.data.mongodb.core.mapping.Field

class JobTimeEntity(

    @Field("start")
    var start: String,

    @Field("end")
    var end: String,
)