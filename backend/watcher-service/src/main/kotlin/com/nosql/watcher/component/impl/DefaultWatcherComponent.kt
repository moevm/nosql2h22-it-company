package com.nosql.watcher.component.impl

import com.nosql.watcher.common.exception.ApplicationException
import com.nosql.watcher.common.exception.DataIntegrityViolationApplicationException
import com.nosql.watcher.common.exception.NotFoundException
import com.nosql.watcher.common.logger.logBefore
import com.nosql.watcher.common.logger.logFailed
import com.nosql.watcher.common.logger.logSuccess
import com.nosql.watcher.common.logger.logger
import com.nosql.watcher.component.WatcherComponent
import com.nosql.watcher.entity.WatcherEntity
import com.nosql.watcher.repository.WatcherRepository
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.bson.types.ObjectId
import org.slf4j.Logger
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import java.util.Date

@Component
class DefaultWatcherComponent(
    private val watcherRepository: WatcherRepository,
): WatcherComponent {

    private val log: Logger by logger()

    override suspend fun save(watcher: WatcherEntity): WatcherEntity {

        val operationDetails = "Save 'watcher' record with"

        log.logBefore(operationDetails)

        return watcherRepository.save(watcher)
            .onErrorMap { handleError(it, operationDetails) }
            .doOnSuccess { log.logSuccess(operationDetails) }
            .awaitSingle()
    }

    override suspend fun get(watcherId: ObjectId): WatcherEntity {

        val operationDetails = "Get 'watcher' record with id = '$watcherId'"

        log.logBefore(operationDetails)

        return watcherRepository.findById(watcherId)
            .onErrorMap { handleError(it, operationDetails) }
            .doOnSuccess { log.logSuccess(operationDetails) }
            .awaitSingle()
    }

    override suspend fun getAll(pageable: Pageable): List<WatcherEntity> {

        val operationDetails = "Get all 'watcher' records"

        log.logBefore(operationDetails)

        return watcherRepository.findAllByIdNotNull(pageable)
            .onErrorMap { handleError(it, operationDetails) }
            .collectList()
            .doOnSuccess { log.logSuccess(operationDetails) }
            .awaitSingle()
    }

    override suspend fun getAllByUserIdAndDate(
        userId: ObjectId,
        from: Date,
        to: Date,
        pageable: Pageable,
    ): List<WatcherEntity> {

        val operationDetails = "Get all 'watcher' records with userId = '$userId'"

        log.logBefore(operationDetails)

        return watcherRepository.findAllByUserIdAndDateBetween(userId, from, to, pageable)
            .onErrorMap { handleError(it, operationDetails) }
            .collectList()
            .doOnSuccess { log.logSuccess(operationDetails) }
            .awaitSingle()
    }

    override suspend fun getAllByUserIdsAndDate(
        userIds: List<ObjectId>,
        from: Date,
        to: Date,
        pageable: Pageable,
    ): List<WatcherEntity> {

        val operationDetails = "Get all 'watcher' records with userId in $userIds"

        log.logBefore(operationDetails)

        return watcherRepository.findAllByUserIdInAndDateBetween(userIds, from, to, pageable)
            .onErrorMap { handleError(it, operationDetails) }
            .collectList()
            .doOnSuccess { log.logSuccess(operationDetails) }
            .awaitSingle()
    }

    override suspend fun getAllByUserIdAndDateAndProjectId(
        userId: ObjectId,
        projectId: ObjectId,
        from: Date, to:
        Date, pageable:
        Pageable
    ): List<WatcherEntity> {

        val operationDetails = "Get all 'watcher' records with userId = '$userId' and projectId = '$projectId'"

        log.logBefore(operationDetails)

        return watcherRepository.findAllByUserIdAndProjectIdAndDateBetween(userId, projectId, from, to, pageable)
            .onErrorMap { handleError(it, operationDetails) }
            .collectList()
            .doOnSuccess { log.logSuccess(operationDetails) }
            .awaitSingle()
    }

    override suspend fun update(watcher: WatcherEntity): WatcherEntity {

        val operationDetails = "Update 'watcher' record with id = ${watcher.id!!.toHexString()}"

        log.logBefore(operationDetails)

        return watcherRepository.save(watcher)
            .onErrorMap { handleError(it, operationDetails) }
            .doOnSuccess { log.logSuccess(operationDetails) }
            .awaitSingle()
    }

    override suspend fun delete(watcherId: ObjectId) {

        val operationDetails = "Delete 'watcher' record with id = ${watcherId.toHexString()}"

        log.logBefore(operationDetails)

        assertUserExists(watcherId)

        watcherRepository.deleteById(watcherId)
            .onErrorMap { handleError(it, operationDetails) }
            .doOnSuccess { log.logSuccess(operationDetails) }
            .awaitSingleOrNull()
    }

    private suspend fun assertUserExists(personId: ObjectId) {
        if (!exists(personId))
            throw NotFoundException(description = "User with id = '${personId}' not found")
    }

    private suspend fun exists(personId: ObjectId): Boolean {
        val operationDetails = "Check if 'person' record by id = '$personId' exists"

        log.logBefore(operationDetails)

        return watcherRepository.existsById(personId)
            .onErrorMap { handleError(it, operationDetails) }
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