package com.nosql.watcher.common.auth

import com.nosql.watcher.common.exception.ApplicationException
import com.nosql.watcher.common.exception.enumerator.ApplicationExceptionCodeEnum.UNAUTHORIZED_EXCEPTION
import org.springframework.http.HttpStatus.UNAUTHORIZED

/**
 * User's authentication required but not present
 */
open class UserAuthNotPresentApiException : ApplicationException(
    status = UNAUTHORIZED,
    code = UNAUTHORIZED_EXCEPTION,
    description =  UNAUTHORIZED_EXCEPTION.message,
)
