package com.nosql.document.common.auth

import com.nosql.document.common.exception.ApplicationException
import com.nosql.document.common.exception.enumerator.ApplicationExceptionCodeEnum.UNAUTHORIZED_EXCEPTION
import org.springframework.http.HttpStatus.UNAUTHORIZED

open class UserAuthNotPresentApiException : ApplicationException(
    status = UNAUTHORIZED,
    code = UNAUTHORIZED_EXCEPTION,
    description =  UNAUTHORIZED_EXCEPTION.message,
)
