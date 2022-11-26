package com.nosql.personservice.exception

import com.nosql.personservice.common.exception.ApplicationException
import com.nosql.personservice.common.exception.enumerator.ApplicationExceptionCodeEnum.USER_ALREADY_EXISTS_EXCEPTION
import org.springframework.http.HttpStatus.BAD_REQUEST

class UserAlreadyExistsException(
    description: String,
) : ApplicationException(
    status = BAD_REQUEST,
    code = USER_ALREADY_EXISTS_EXCEPTION,
    description = description,
)
