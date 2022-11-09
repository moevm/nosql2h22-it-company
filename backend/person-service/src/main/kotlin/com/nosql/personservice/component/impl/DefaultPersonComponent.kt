package com.nosql.personservice.component.impl

import com.nosql.personservice.common.exception.ApplicationException
import com.nosql.personservice.common.exception.DataIntegrityViolationApplicationException
import com.nosql.personservice.common.logger.logBefore
import com.nosql.personservice.common.logger.logFailed
import com.nosql.personservice.common.logger.logSuccess
import com.nosql.personservice.common.logger.logger
import com.nosql.personservice.component.PersonComponent
import com.nosql.personservice.entity.PersonEntity
import com.nosql.personservice.repository.PersonRepository
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.bson.types.ObjectId
import org.slf4j.Logger
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Component

@Component
class DefaultPersonComponent(
    private val personRepository: PersonRepository,
) : PersonComponent {

    private val log: Logger by logger()

    override suspend fun save(person: PersonEntity): PersonEntity {
        val operationDetails = "Save 'person' record with id = ${person.id}"

        log.logBefore(operationDetails)

        return personRepository.save(person)
            .onErrorMap { handleError(it, operationDetails) }
            .doOnSuccess { log.logSuccess(operationDetails) }
            .awaitSingle()
    }

    override suspend fun delete(userObjectId: ObjectId) {
        val operationDetails = "Delete 'person' record with id = ${userObjectId.toHexString()}"

        log.logBefore(operationDetails)

        personRepository.deleteById(userObjectId)
            .onErrorMap { handleError(it, operationDetails) }
            .doOnSuccess { log.logSuccess(operationDetails) }
            .awaitSingleOrNull()
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