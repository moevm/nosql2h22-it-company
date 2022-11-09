package com.nosql.personservice.component

import com.nosql.personservice.entity.PersonEntity
import org.bson.types.ObjectId

interface PersonComponent {
    suspend fun save(person: PersonEntity): PersonEntity
    suspend fun delete(userObjectId: ObjectId)
}