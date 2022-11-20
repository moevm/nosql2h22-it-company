package com.nosql.authservice.repository

import com.nosql.authservice.entity.UserEntity
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface UserRepository : ReactiveMongoRepository<UserEntity, ObjectId> {

    fun existsByLogin(login: String): Mono<Boolean>

    fun getByLoginAndPasswordHash(login: String, passwordHash: String): Mono<UserEntity>
}
