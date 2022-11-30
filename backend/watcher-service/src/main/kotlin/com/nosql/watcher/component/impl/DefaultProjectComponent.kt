package com.nosql.watcher.component.impl

import com.nosql.watcher.common.exception.ApplicationException
import com.nosql.watcher.common.exception.DataIntegrityViolationApplicationException
import com.nosql.watcher.common.logger.logBefore
import com.nosql.watcher.common.logger.logFailed
import com.nosql.watcher.common.logger.logSuccess
import com.nosql.watcher.common.logger.logger
import com.nosql.watcher.component.ProjectComponent
import com.nosql.watcher.repository.ProjectRepository
import kotlinx.coroutines.reactor.awaitSingle
import org.bson.types.ObjectId
import org.slf4j.Logger
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Component

@Component
class DefaultProjectComponent(
    private val projectRepository: ProjectRepository,
): ProjectComponent {

    private val log: Logger by logger()

    override suspend fun getIdByName(name: String): ObjectId {

        val operationDetails = "Get 'project' record id with name = '$name'"

        log.logBefore(operationDetails)

        return projectRepository.findByName(name)
            .onErrorMap { handleError(it, operationDetails) }
            .doOnSuccess { log.logSuccess(operationDetails) }
            .awaitSingle()
            .id!!
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