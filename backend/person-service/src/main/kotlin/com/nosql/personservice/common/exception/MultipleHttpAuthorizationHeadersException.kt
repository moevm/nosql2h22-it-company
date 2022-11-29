package com.nosql.personservice.common.exception

import com.nosql.personservice.common.exception.enumerator.ApplicationExceptionCodeEnum.MULTIPLE_AUTHORIZATION_HEADERS
import org.springframework.http.HttpStatus

class MultipleHttpAuthorizationHeadersException : ApplicationException(
    status = HttpStatus.FORBIDDEN,
    code = MULTIPLE_AUTHORIZATION_HEADERS,
    description = MULTIPLE_AUTHORIZATION_HEADERS.message,
)