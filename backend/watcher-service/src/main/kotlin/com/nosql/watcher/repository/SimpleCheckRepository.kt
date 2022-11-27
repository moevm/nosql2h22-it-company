package com.nosql.watcher.repository

import com.nosql.watcher.entity.SimpleCheckEntity
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface SimpleCheckRepository : ReactiveMongoRepository<SimpleCheckEntity, String>