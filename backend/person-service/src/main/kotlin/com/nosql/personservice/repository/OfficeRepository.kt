package com.nosql.personservice.repository

import com.nosql.personservice.entity.OfficeEntity
import org.bson.types.ObjectId
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface OfficeRepository : ReactiveMongoRepository<OfficeEntity, ObjectId> {
    fun findAllByIdNotNull(pageable: Pageable): Flux<OfficeEntity>
}