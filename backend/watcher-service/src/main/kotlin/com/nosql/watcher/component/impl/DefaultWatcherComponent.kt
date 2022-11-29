package com.nosql.watcher.component.impl

import com.nosql.watcher.common.exception.ApplicationException
import com.nosql.watcher.common.exception.DataIntegrityViolationApplicationException
import com.nosql.watcher.common.logger.logBefore
import com.nosql.watcher.common.logger.logFailed
import com.nosql.watcher.common.logger.logSuccess
import com.nosql.watcher.common.logger.logger
import com.nosql.watcher.component.WatcherComponent
import com.nosql.watcher.entity.WatcherEntity
import com.nosql.watcher.repository.WatcherRepository
import kotlinx.coroutines.reactor.awaitSingle
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

    override suspend fun getAllByUserIdAndDate(userId: ObjectId, from: Date, to: Date, pageable: Pageable): List<WatcherEntity> {

        val operationDetails = "Get all 'watcher' records with userId = 'userId'"

        log.logBefore(operationDetails)

        return watcherRepository.findAllByUserIdAndDateBetween(userId, from, to, pageable)
            .onErrorMap { handleError(it, operationDetails) }
            .collectList()
            .doOnSuccess { log.logSuccess(operationDetails) }
            .awaitSingle()
    }

    override suspend fun update(watcher: WatcherEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(watcherId: ObjectId) {
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