package com.nosql.personservice.component.impl

import com.nosql.personservice.common.exception.ApplicationException
import com.nosql.personservice.common.exception.DataIntegrityViolationApplicationException
import com.nosql.personservice.common.logger.logBefore
import com.nosql.personservice.common.logger.logFailed
import com.nosql.personservice.common.logger.logSuccess
import com.nosql.personservice.common.logger.logger
import com.nosql.personservice.component.ProjectComponent
import com.nosql.personservice.entity.ProjectEntity
import com.nosql.personservice.repository.ProjectRepository
import kotlinx.coroutines.reactor.awaitSingle
import org.bson.types.ObjectId
import org.slf4j.Logger
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class DefaultProjectComponent(
    private val projectRepository: ProjectRepository,
) : ProjectComponent {

    private val log: Logger by logger()
    override suspend fun saveAll(projects: List<ProjectEntity>): List<ProjectEntity> {

        val operationDetails = "Save 'project' records"

        log.logBefore(operationDetails)

        return projectRepository.saveAll(projects)
            .onErrorMap { handleError(it, operationDetails) }
            .collectList()
            .doOnSuccess { log.logSuccess(operationDetails) }
            .awaitSingle()
    }

    override suspend fun getById(id: ObjectId): ProjectEntity {

        val operationDetails = "Get 'project' record with id = '$id'"

        log.logBefore(operationDetails)

        return projectRepository.findById(id)
            .onErrorMap { handleError(it, operationDetails) }
            .doOnSuccess { log.logSuccess(operationDetails) }
            .awaitSingle()
    }

    override suspend fun getAll(pageable: Pageable): List<ProjectEntity> {

        val operationDetails = "Get 'project' records"

        log.logBefore(operationDetails)

        return projectRepository.findAllByIdNotNull(pageable)
            .onErrorMap { handleError(it, operationDetails) }
            .collectList()
            .doOnSuccess { log.logSuccess(operationDetails) }
            .awaitSingle()
    }

    override suspend fun getAllByIds(ids: List<ObjectId>): List<ProjectEntity> {

        val operationDetails = "Get 'project' records by ids in '$ids'"

        log.logBefore(operationDetails)

        return projectRepository.findAllById(ids)
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