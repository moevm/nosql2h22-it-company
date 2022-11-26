package com.nosql.personservice.entity

import org.springframework.data.mongodb.core.mapping.Field
import java.util.Date

class PassportDataEntity(

    @Field("number")
    var number: String,

    @Field("issuedPlace")
    var issuedPlace: String,

    @Field("issuedDate")
    var issuedDate: Date,
)