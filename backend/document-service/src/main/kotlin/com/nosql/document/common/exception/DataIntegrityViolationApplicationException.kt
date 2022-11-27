package com.nosql.document.common.exception

import com.nosql.document.common.exception.enumerator.ApplicationExceptionCodeEnum.DATA_INTEGRITY_VIOLATION_EXCEPTION
import org.springframework.http.HttpStatus

class DataIntegrityViolationApplicationException(
    cause: Throwable? = null,
) : ApplicationException(
    status = HttpStatus.INTERNAL_SERVER_ERROR,
    code = DATA_INTEGRITY_VIOLATION_EXCEPTION,
    description = DATA_INTEGRITY_VIOLATION_EXCEPTION.message,
    cause = cause,
)
