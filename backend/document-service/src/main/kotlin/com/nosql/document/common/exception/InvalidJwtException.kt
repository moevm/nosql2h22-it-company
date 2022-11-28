package com.nosql.document.common.exception

import com.nosql.document.common.exception.enumerator.ApplicationExceptionCodeEnum.INVALID_JWT
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.FORBIDDEN
import org.springframework.http.HttpStatus.UNAUTHORIZED

class InvalidJwtException : ApplicationException(
    status = UNAUTHORIZED,
    code = INVALID_JWT,
    description = INVALID_JWT.message,
)