package com.nosql.authservice.common.exception.enumerator

enum class ApplicationExceptionCodeEnum(val message: String) {
    DATA_INTEGRITY_VIOLATION_EXCEPTION("Data integrity violation exception"),
    DECODING_EXCEPTION("Invalid request"),
    INVALID_JWT("The JWT is invalid, and it cannot be parsed"),
    MULTIPLE_AUTHORIZATION_HEADERS("Multiple authorization headers were found in the request"),
    NOT_FOUND_EXCEPTION("Not found any record by provided parameters"),
    UNAUTHORIZED_EXCEPTION("Forbidden"),
    UNEXPECTED_EXCEPTION("Unexpected application exception"),
    USER_ALREADY_EXISTS_EXCEPTION("User with given id already exists"),
    VALIDATION_EXCEPTION("Invalid data"),
}
