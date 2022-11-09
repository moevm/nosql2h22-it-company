package com.nosql.personservice.repository

import com.nosql.personservice.entity.SimpleCheckEntity
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface SimpleCheckRepository : ReactiveMongoRepository<SimpleCheckEntity, String>