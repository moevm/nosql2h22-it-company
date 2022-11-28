package com.nosql.document.repository

import com.nosql.document.entity.DocumentEntity
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface DocumentRepository : ReactiveMongoRepository<DocumentEntity, ObjectId> {
}