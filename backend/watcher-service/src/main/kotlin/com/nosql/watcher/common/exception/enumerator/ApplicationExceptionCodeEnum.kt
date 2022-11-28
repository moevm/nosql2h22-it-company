package com.nosql.watcher.common.exception.enumerator

enum class ApplicationExceptionCodeEnum(val message: String) {
    DATA_INTEGRITY_VIOLATION_EXCEPTION("Data integrity violation exception"),
    DECODING_EXCEPTION("Invalid request"),
    INVALID_JWT("The JWT is invalid, and it cannot be parsed"),
    MULTIPLE_AUTHORIZATION_HEADERS("Multiple authorization headers were found in the request"),
    NOT_FOUND_EXCEPTION("Not found any record by provided parameters"),
    UNEXPECTED_EXCEPTION("Unexpected application exception"),
    UNAUTHORIZED_EXCEPTION("Forbidden"),
    VALIDATION_EXCEPTION("Invalid data"),
}
