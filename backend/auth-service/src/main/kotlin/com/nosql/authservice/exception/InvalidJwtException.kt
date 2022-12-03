package com.nosql.authservice.exception

import com.nosql.authservice.common.exception.ApplicationException
import com.nosql.authservice.common.exception.enumerator.ApplicationExceptionCodeEnum
import org.springframework.http.HttpStatus

class InvalidJwtException(
    description: String = "Invalid JWT",
    cause: Throwable? = null,
) : ApplicationException(
    status = HttpStatus.BAD_REQUEST,
    code = ApplicationExceptionCodeEnum.INVALID_JWT,
    description = description,
    cause = cause,
)
