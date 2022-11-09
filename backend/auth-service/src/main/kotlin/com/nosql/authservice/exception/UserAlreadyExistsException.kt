package com.nosql.authservice.exception

import com.nosql.authservice.common.exception.ApplicationException
import com.nosql.authservice.common.exception.enumerator.ApplicationExceptionCodeEnum.USER_ALREADY_EXISTS_EXCEPTION
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR

class UserAlreadyExistsException(
    description: String,
) : ApplicationException(
    status = INTERNAL_SERVER_ERROR,
    code = USER_ALREADY_EXISTS_EXCEPTION,
    description = description,
)
