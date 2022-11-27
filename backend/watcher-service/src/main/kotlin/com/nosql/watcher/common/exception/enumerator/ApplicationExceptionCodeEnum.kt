package com.nosql.watcher.common.exception.enumerator

enum class ApplicationExceptionCodeEnum(val message: String) {
    DATA_INTEGRITY_VIOLATION_EXCEPTION("Data integrity violation exception"),
    DECODING_EXCEPTION("Invalid request"),
    NOT_FOUND_EXCEPTION("Not found any record by provided parameters"),
    UNEXPECTED_EXCEPTION("Unexpected application exception"),
    USER_ALREADY_EXISTS_EXCEPTION("User with given id already exists"),
    VALIDATION_EXCEPTION("Invalid data"),
}
