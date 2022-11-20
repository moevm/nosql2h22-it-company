package com.nosql.authservice.component.impl

import com.nosql.authservice.common.exception.ApplicationException
import com.nosql.authservice.common.exception.DataIntegrityViolationApplicationException
import com.nosql.authservice.common.exception.NotFoundException
import com.nosql.authservice.common.logger.logBefore
import com.nosql.authservice.common.logger.logFailed
import com.nosql.authservice.common.logger.logSuccess
import com.nosql.authservice.common.logger.logger
import com.nosql.authservice.component.UserComponent
import com.nosql.authservice.dto.UserDto
import com.nosql.authservice.entity.UserEntity
import com.nosql.authservice.exception.UserAlreadyExistsException
import com.nosql.authservice.repository.UserRepository
import kotlinx.coroutines.reactor.awaitSingle
import org.slf4j.Logger
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import reactor.kotlin.core.publisher.toMono

@Component
class DefaultUserComponent(
    private val userRepository: UserRepository,
) : UserComponent {

    private val log: Logger by logger()

    @Transactional
    override suspend fun saveIfNotExists(user: UserEntity): UserEntity {
        val operationDetails = "Save if not exists 'auth' record with login = ${user.login}"

        log.logBefore(operationDetails)

        assertUserNotExists(user)

        return userRepository.save(user)
            .onErrorMap { handleError(it, operationDetails) }
            .doOnSuccess { log.logSuccess(operationDetails) }
            .awaitSingle()
    }

    @Transactional(readOnly = true)
    override suspend fun existsByLogin(login: String): Boolean {
        val operationDetails = "Check if 'auth' record by login = '$login' exists"

        log.logBefore(operationDetails)

        return userRepository.existsByLogin(login)
            .onErrorMap { handleError(it, operationDetails) }
            .doOnSuccess { log.logSuccess(operationDetails) }
            .awaitSingle()
    }

    @Transactional(readOnly = true)
    override suspend fun getByLoginAndPasswordHash(userDto: UserDto): UserEntity {
        val (login, passwordHash) = userDto
        val operationDetails = "Get 'auth' record by login = '$login'"

        log.logBefore(operationDetails)

        return userRepository.getByLoginAndPasswordHash(login, passwordHash)
            .switchIfEmpty(
                NotFoundException(
                    description = "Not found 'auth' record by given login = '$login'"
                ).toMono()
            )
            .onErrorMap { handleError(it, operationDetails) }
            .doOnSuccess { log.logSuccess(operationDetails) }
            .awaitSingle()
    }

    private suspend fun assertUserNotExists(user: UserEntity) {
        if (existsByLogin(user.login))
            throw UserAlreadyExistsException("User with login = '${user.login}' already exists")
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
