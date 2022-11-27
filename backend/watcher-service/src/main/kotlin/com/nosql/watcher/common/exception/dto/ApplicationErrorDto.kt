package com.nosql.watcher.common.exception.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.nosql.watcher.common.exception.enumerator.ApplicationExceptionCodeEnum.UNEXPECTED_EXCEPTION
import com.nosql.watcher.common.exception.ApplicationException
import com.nosql.watcher.constants.jackson.DATE_TIME_WITH_UTC_TIME_ZONE_PATTERN
import com.nosql.watcher.constants.jackson.UTC_TIME_ZONE
import java.time.OffsetDateTime
import java.util.UUID

data class ApplicationErrorDto(
    val id: UUID = UUID.randomUUID(),
    val code: String?,
    val description: String?,
    @JsonFormat(pattern = DATE_TIME_WITH_UTC_TIME_ZONE_PATTERN, timezone = UTC_TIME_ZONE)
    var timestamp: OffsetDateTime = OffsetDateTime.now(),
) {
    constructor(ex: ApplicationException) : this(
        code = ex.code.name,
        description = ex.code.message
    )

    constructor(ex: Exception) : this(
        code = "${UNEXPECTED_EXCEPTION.name}.${ex.javaClass.simpleName}",
        description = ex.message
    )
}
