package com.nosql.personservice.repository

import com.nosql.personservice.entity.PersonEntity
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : ReactiveMongoRepository<PersonEntity, ObjectId>