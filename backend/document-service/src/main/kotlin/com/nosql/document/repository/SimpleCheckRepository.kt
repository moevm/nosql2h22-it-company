package com.nosql.document.repository

import com.nosql.document.entity.SimpleCheckEntity
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface SimpleCheckRepository : ReactiveMongoRepository<SimpleCheckEntity, String>