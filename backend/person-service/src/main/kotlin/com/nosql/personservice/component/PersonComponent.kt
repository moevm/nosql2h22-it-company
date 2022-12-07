package com.nosql.personservice.component

import com.nosql.personservice.entity.PersonEntity
import org.bson.types.ObjectId
import org.springframework.data.domain.Pageable
import java.util.Date

interface PersonComponent {
    suspend fun save(person: PersonEntity): PersonEntity

    suspend fun saveAll(persons: List<PersonEntity>): List<PersonEntity>

    suspend fun get(personId: ObjectId): PersonEntity

    suspend fun getAll(pageable: Pageable): List<PersonEntity>

    suspend fun getAllByName(name: String, pageable: Pageable): List<PersonEntity>

    suspend fun extendedGet(
        name: String,
        surname: String,
        patronymic: String,
        sex: List<String>,
        position: String,
        status: String,
        startAge: Date,
        endAge: Date,
        pageable: Pageable,
    ): List<PersonEntity>

    suspend fun update(person: PersonEntity): PersonEntity

    suspend fun delete(personId: ObjectId)
}