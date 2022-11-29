package com.nosql.watcher.repository

import com.nosql.watcher.entity.ProjectEntity
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

interface ProjectRepository : ReactiveMongoRepository<ProjectEntity, ObjectId> {

    fun findByName(name: String): Mono<ProjectEntity>
}