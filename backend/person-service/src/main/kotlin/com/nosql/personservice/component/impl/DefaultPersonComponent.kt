package com.nosql.personservice.component.impl

import com.nosql.personservice.common.exception.ApplicationException
import com.nosql.personservice.common.exception.DataIntegrityViolationApplicationException
import com.nosql.personservice.common.exception.NotFoundException
import com.nosql.personservice.common.logger.logBefore
import com.nosql.personservice.common.logger.logFailed
import com.nosql.personservice.common.logger.logSuccess
import com.nosql.personservice.common.logger.logger
import com.nosql.personservice.component.PersonComponent
import com.nosql.personservice.entity.PersonEntity
import com.nosql.personservice.exception.UserAlreadyExistsException
import com.nosql.personservice.repository.PersonRepository
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.bson.types.ObjectId
import org.slf4j.Logger
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import reactor.kotlin.core.publisher.toMono
import java.util.Date

@Component
class DefaultPersonComponent(
    private val personRepository: PersonRepository,
) : PersonComponent {

    private val log: Logger by logger()

    override suspend fun save(person: PersonEntity): PersonEntity {
        val operationDetails = "Save 'person' record with id = ${person.id.toHexString()}"

        log.logBefore(operationDetails)

        assertUserNotExists(person)

        return personRepository.save(person)
            .onErrorMap { handleError(it, operationDetails) }
            .doOnSuccess { log.logSuccess(operationDetails) }
            .awaitSingle()
    }

    override suspend fun saveAll(persons: List<PersonEntity>): List<PersonEntity> {
        val operationDetails = "Save 'person' records"

        log.logBefore(operationDetails)

        return personRepository.saveAll(persons)
            .onErrorMap { handleError(it, operationDetails) }
            .collectList()
            .doOnSuccess { log.logSuccess(operationDetails) }
            .awaitSingle()
    }

    override suspend fun get(personId: ObjectId): PersonEntity {
        val operationDetails = "Get 'person' record with id = ${personId.toHexString()}"

        log.logBefore(operationDetails)

        return personRepository.findById(personId)
            .switchIfEmpty(NotFoundException(description = "Not found 'person' record by id = '$personId'").toMono())
            .onErrorMap { handleError(it, operationDetails) }
            .doOnSuccess { log.logSuccess(operationDetails) }
            .awaitSingle()
    }

    override suspend fun getAll(pageable: Pageable): List<PersonEntity> {
        val operationDetails = "Get 'person' records"

        log.logBefore(operationDetails)

        return personRepository.findAllByIdNotNull(pageable)
            .onErrorMap { handleError(it, operationDetails) }
            .collectList()
            .doOnSuccess { log.logSuccess(operationDetails) }
            .awaitSingle()
    }

    override suspend fun getAllByName(name: String, pageable: Pageable): List<PersonEntity> {
        val operationDetails = "Get 'person' records with name like '$name'"

        log.logBefore(operationDetails)

        return personRepository.findAllByNameIsLikeIgnoreCase(name, pageable)
            .onErrorMap { handleError(it, operationDetails) }
            .collectList()
            .doOnSuccess { log.logSuccess(operationDetails) }
            .awaitSingle()
    }

    override suspend fun extendedGet(
        name: String,
        surname: String,
        patronymic: String,
        sex: String,
        position: String,
        status: String,
        startAge: Date,
        endAge: Date,
        pageable: Pageable,
    ): List<PersonEntity> {
        val operationDetails = "Get 'person' records with name like '$name', surname like '$surname', patronymic " +
                "like '$patronymic', sex like '$sex', position like '$position', status like '$status', startAge " +
                "like '$startAge', endAge like '$endAge'"

        log.logBefore(operationDetails)

        return personRepository.findExtended(
            name = name,
            surname = surname,
            patronymic = patronymic,
            sex = sex,
            position = position,
            status = status,
            startAge = startAge,
            endAge = endAge,
            pageable = pageable
        ).onErrorMap { handleError(it, operationDetails) }
            .collectList()
            .doOnSuccess { log.logSuccess(operationDetails) }
            .awaitSingle()
    }

    override suspend fun update(person: PersonEntity): PersonEntity {
        val operationDetails = "Update 'person' record with id = ${person.id.toHexString()}"

        log.logBefore(operationDetails)

        return personRepository.save(person)
            .onErrorMap { handleError(it, operationDetails) }
            .doOnSuccess { log.logSuccess(operationDetails) }
            .awaitSingle()
    }


    override suspend fun delete(personId: ObjectId) {
        val operationDetails = "Delete 'person' record with id = ${personId.toHexString()}"

        log.logBefore(operationDetails)

        assertUserExists(personId)

        personRepository.deleteById(personId)
            .onErrorMap { handleError(it, operationDetails) }
            .doOnSuccess { log.logSuccess(operationDetails) }
            .awaitSingleOrNull()
    }

    private suspend fun exists(personId: ObjectId): Boolean {
        val operationDetails = "Check if 'person' record by id = '$personId' exists"

        log.logBefore(operationDetails)

        return personRepository.existsById(personId)
            .onErrorMap { handleError(it, operationDetails) }
            .doOnSuccess { log.logSuccess(operationDetails) }
            .awaitSingle()
    }

    private suspend fun assertUserExists(personId: ObjectId) {
        if (!exists(personId))
            throw NotFoundException(description = "User with id = '${personId}' not found")
    }

    private suspend fun assertUserNotExists(person: PersonEntity) {
        if (exists(person.id))
            throw UserAlreadyExistsException("User with id = '${person.id}' already exists")
    }

    private fun handleError(
        error: Throwable,
        operationDetails: String,
    ): ApplicationException {

        log.logFailed(operationDetails, error)

        val operationFailedWithMessage = "$operationDetails: failed with"
        val errorDetails = if (error is ApplicationException) error.description else "something went wrong"

        return when (error) {
            is DataIntegrityViolationException -> {
                DataIntegrityViolationApplicationException()
            }

            is ApplicationException -> {
                error.apply { description = "$operationFailedWithMessage error: $errorDetails" }
            }

            else -> {
                ApplicationException(
                    description = "$operationFailedWithMessage unexpected error: $errorDetails",
                )
            }
        }
    }
}