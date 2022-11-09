package com.nosql.authservice.exception

import com.nosql.authservice.common.exception.enumerator.ApplicationExceptionCodeEnum
import org.springframework.http.HttpStatus

open class ApplicationException(
    open val status: HttpStatus? = null,
    val code: ApplicationExceptionCodeEnum,
    open var description: String? = null,
    override val cause: Throwable? = null,
) : RuntimeException(description, cause)
