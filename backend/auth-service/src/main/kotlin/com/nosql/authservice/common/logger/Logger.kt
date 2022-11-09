package com.nosql.authservice.common.logger

import com.nosql.authservice.common.exception.ApplicationException
import org.slf4j.Logger
import org.slf4j.LoggerFactory

fun <R : Any> R.logger() = lazy { LoggerFactory.getLogger(this::class.java.name) }

fun Logger.logBefore(operationDetails: String) = info(
    "$operationDetails: starting...",
)

fun Logger.logSuccess(operationDetails: String, additionalDetails: String? = "") = info(
    "$operationDetails: successfully processed $additionalDetails",
)

fun Logger.logFailed(operationDetails: String, error: Throwable) {
    val errorMessage = when (error) {
        is ApplicationException -> error.description
        else -> "[{${error::class.simpleName}] ${error.message}"
    }
    error("$operationDetails: failed with error \"$errorMessage\"")
}
