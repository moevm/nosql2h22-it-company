package com.nosql.personservice.common.exception

import com.nosql.personservice.common.exception.enumerator.ApplicationExceptionCodeEnum.NOT_FOUND_EXCEPTION
import org.springframework.http.HttpStatus

open class NotFoundException(
    status: HttpStatus = HttpStatus.NOT_FOUND,
    description: String? = null,
) : ApplicationException(
    status = status,
    code = NOT_FOUND_EXCEPTION,
    description = description,
)
