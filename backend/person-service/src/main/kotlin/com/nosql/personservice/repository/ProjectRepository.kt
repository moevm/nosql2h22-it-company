package com.nosql.personservice.repository

import com.nosql.personservice.entity.ProjectEntity
import org.bson.types.ObjectId
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux

interface ProjectRepository : ReactiveMongoRepository<ProjectEntity, ObjectId> {
    fun findAllByIdNotNull(pageable: Pageable): Flux<ProjectEntity>

    fun findAllByNameIn(names: List<String>): Flux<ProjectEntity>
}