package com.nosql.document.common.exception

import com.nosql.document.common.exception.enumerator.ApplicationExceptionCodeEnum.NOT_FOUND_EXCEPTION
import org.springframework.http.HttpStatus.NOT_FOUND

open class NotFoundException(
    description: String? = null,
) : ApplicationException(
    status = NOT_FOUND,
    code = NOT_FOUND_EXCEPTION,
    description = description,
)
