package com.nosql.personservice.common.exception

import com.nosql.personservice.common.exception.enumerator.ApplicationExceptionCodeEnum
import com.nosql.personservice.common.exception.enumerator.ApplicationExceptionCodeEnum.UNEXPECTED_EXCEPTION
import org.springframework.http.HttpStatus

open class ApplicationException(
    open val status: HttpStatus? = null,
    val code: ApplicationExceptionCodeEnum = UNEXPECTED_EXCEPTION,
    open var description: String? = null,
    override val cause: Throwable? = null,
) : RuntimeException(description, cause)
