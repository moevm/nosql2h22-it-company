package com.nosql.document.component.impl

import com.nosql.document.common.exception.ApplicationException
import com.nosql.document.common.exception.DataIntegrityViolationApplicationException
import com.nosql.document.common.logger.logBefore
import com.nosql.document.common.logger.logFailed
import com.nosql.document.common.logger.logSuccess
import com.nosql.document.common.logger.logger
import com.nosql.document.component.DocumentComponent
import com.nosql.document.entity.DocumentEntity
import com.nosql.document.repository.DocumentRepository
import kotlinx.coroutines.reactor.awaitSingle
import org.bson.types.ObjectId
import org.slf4j.Logger
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class DefaultDocumentComponent(
    private val documentRepository: DocumentRepository,
): DocumentComponent {

    private val log: Logger by logger()

    override suspend fun save(document: DocumentEntity): DocumentEntity {

        val operationDetails = "Save 'document' record with"

        log.logBefore(operationDetails)

        return documentRepository.save(document)
            .onErrorMap { handleError(it, operationDetails) }
            .doOnSuccess { log.logSuccess(operationDetails) }
            .awaitSingle()
    }

    override suspend fun get(documentId: ObjectId): DocumentEntity {

        val operationDetails = "Get 'document' record with id = '$documentId'"

        log.logBefore(operationDetails)

        return documentRepository.findById(documentId)
            .onErrorMap { handleError(it, operationDetails) }
            .doOnSuccess { log.logSuccess(operationDetails) }
            .awaitSingle()
    }

    override suspend fun getAll(pageable: Pageable): List<DocumentEntity> {

        val operationDetails = "Get all 'document' records"

        log.logBefore(operationDetails)

        return documentRepository.findAllByIdNotNull(pageable)
            .onErrorMap { handleError(it, operationDetails) }
            .collectList()
            .doOnSuccess { log.logSuccess(operationDetails) }
            .awaitSingle()
    }

    override suspend fun getAllByUserId(userId: ObjectId, pageable: Pageable): List<DocumentEntity> {

        val operationDetails = "Get all 'document' records with userId = 'userId'"

        log.logBefore(operationDetails)

        return documentRepository.findAllByUserId(userId, pageable)
            .onErrorMap { handleError(it, operationDetails) }
            .collectList()
            .doOnSuccess { log.logSuccess(operationDetails) }
            .awaitSingle()
    }

    override suspend fun update(document: DocumentEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(documentId: ObjectId) {
        TODO("Not yet implemented")
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