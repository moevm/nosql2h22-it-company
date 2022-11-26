package com.nosql.personservice.common.exception.enumerator

enum class ApplicationExceptionCodeEnum(val message: String) {
    DATA_INTEGRITY_VIOLATION_EXCEPTION("Data integrity violation error"),
    DECODING_EXCEPTION("Invalid request"),
    NOT_FOUND_EXCEPTION("Not found any record by provided parameters"),
    UNEXPECTED_EXCEPTION("Unexpected application error"),
    VALIDATION_EXCEPTION("Invalid data"),
}
