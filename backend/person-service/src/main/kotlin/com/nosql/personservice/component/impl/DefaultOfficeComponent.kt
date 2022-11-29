package com.nosql.personservice.component.impl

import com.nosql.personservice.common.exception.ApplicationException
import com.nosql.personservice.common.exception.DataIntegrityViolationApplicationException
import com.nosql.personservice.common.logger.logBefore
import com.nosql.personservice.common.logger.logFailed
import com.nosql.personservice.common.logger.logSuccess
import com.nosql.personservice.common.logger.logger
import com.nosql.personservice.component.OfficeComponent
import com.nosql.personservice.entity.OfficeEntity
import com.nosql.personservice.repository.OfficeRepository
import kotlinx.coroutines.reactor.awaitSingle
import org.bson.types.ObjectId
import org.slf4j.Logger
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class DefaultOfficeComponent(
    private val officeRepository: OfficeRepository,
) : OfficeComponent {

    private val log: Logger by logger()
    override suspend fun getById(id: ObjectId): OfficeEntity {

        val operationDetails = "Get 'office' record with id = '$id'"

        log.logBefore(operationDetails)

        return officeRepository.findById(id)
            .onErrorMap { handleError(it, operationDetails) }
            .doOnSuccess { log.logSuccess(operationDetails) }
            .awaitSingle()
    }

    override suspend fun getAll(pageable: Pageable): List<OfficeEntity> {

        val operationDetails = "Get 'office' records"

        log.logBefore(operationDetails)

        return officeRepository.findAllByIdNotNull(pageable)
            .onErrorMap { handleError(it, operationDetails) }
            .collectList()
            .doOnSuccess { log.logSuccess(operationDetails) }
            .awaitSingle()
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