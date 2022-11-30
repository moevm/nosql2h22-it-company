package com.nosql.personservice.common.exception

import com.nosql.personservice.common.exception.enumerator.ApplicationExceptionCodeEnum.INVALID_JWT
import org.springframework.http.HttpStatus.FORBIDDEN

class InvalidJwtException : ApplicationException(
    status = FORBIDDEN,
    code = INVALID_JWT,
    description = INVALID_JWT.message,
)