package com.nosql.authservice.repository

import com.nosql.authservice.entity.SimpleCheckEntity
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface SimpleCheckRepository : ReactiveMongoRepository<SimpleCheckEntity, String>