package com.nosql.personservice.repository

import com.nosql.personservice.entity.UserEntity
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface UserRepository: ReactiveMongoRepository<UserEntity, ObjectId>