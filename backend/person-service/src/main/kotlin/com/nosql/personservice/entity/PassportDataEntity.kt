package com.nosql.personservice.entity

import org.springframework.data.mongodb.core.mapping.Field
import java.util.Date

class PassportDataEntity(

    @Field("number")
    var number: String,

    @Field("issued_place")
    var issuedPlace: String,

    @Field("issued_date")
    var issuedDate: Date,
)