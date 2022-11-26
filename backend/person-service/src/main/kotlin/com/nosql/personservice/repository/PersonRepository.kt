package com.nosql.personservice.repository

import com.nosql.personservice.entity.PersonEntity
import org.bson.types.ObjectId
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import java.util.Date

@Repository
interface PersonRepository : ReactiveMongoRepository<PersonEntity, ObjectId> {

    fun findAllByIdNotNull(pageable: Pageable): Flux<PersonEntity>

    fun findAllByNameIsLike(name: String, pageable: Pageable): Flux<PersonEntity>

    @Query(
        "{'\$and': " +
                "[" +
                "{ 'name' : { '\$regex': ?0 } }, " +
                "{ 'surname' : { '\$regex': ?1 } }, " +
                "{ 'patronymic' : { '\$regex': ?2 } }, " +
                "{ 'sex' : { '\$regex': ?3 } }, " +
                "{ 'position' : { '\$regex': ?4 } }, " +
                "{ 'status' : { '\$regex': ?5 } }, " +
                "{'\$and' : [{ 'birthday' : {'\$lte' : ?6 } }, { 'birthday' : { '\$gte' : ?7 } }]}, " +
                "]" +
                "}"
    )
    fun findExtended(
        name: String,
        surname: String,
        patronymic: String,
        sex: String,
        position: String,
        status: String,
        startAge: Date,
        endAge: Date,
        pageable: Pageable,
    ): Flux<PersonEntity>
}