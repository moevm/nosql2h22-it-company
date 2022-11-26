package com.nosql.personservice.repository

import com.nosql.personservice.entity.OfficeEntity
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface OfficeRepository : ReactiveMongoRepository<OfficeEntity, ObjectId>