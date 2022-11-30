package com.nosql.document.repository

import com.nosql.document.entity.DocumentEntity
import com.nosql.document.enumerator.DocumentStatus
import com.nosql.document.enumerator.DocumentType
import org.bson.types.ObjectId
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface DocumentRepository : ReactiveMongoRepository<DocumentEntity, ObjectId> {

    fun findAllByIdNotNullAndTypeInAndStatusIn(
        types: List<DocumentType>,
        status: List<DocumentStatus>,
        pageable: Pageable
    ): Flux<DocumentEntity>

    fun findAllByUserId(userId: ObjectId, pageable: Pageable): Flux<DocumentEntity>
}