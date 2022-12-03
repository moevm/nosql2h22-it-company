package com.nosql.authservice.common.exception

import com.nosql.authservice.common.exception.enumerator.ApplicationExceptionCodeEnum.MULTIPLE_AUTHORIZATION_HEADERS
import org.springframework.http.HttpStatus.UNAUTHORIZED

class MultipleHttpAuthorizationHeadersException : ApplicationException(
    status = UNAUTHORIZED,
    code = MULTIPLE_AUTHORIZATION_HEADERS,
    description = MULTIPLE_AUTHORIZATION_HEADERS.message,
)
