package com.nosql.watcher.repository

import com.nosql.watcher.entity.WatcherEntity
import org.bson.types.ObjectId
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux
import java.util.Date

interface WatcherRepository : ReactiveMongoRepository<WatcherEntity, ObjectId> {

    fun findAllByIdNotNull(pageable: Pageable): Flux<WatcherEntity>

    fun findAllByUserIdAndDateBetween(userId: ObjectId, from: Date, to: Date, pageable: Pageable): Flux<WatcherEntity>
}