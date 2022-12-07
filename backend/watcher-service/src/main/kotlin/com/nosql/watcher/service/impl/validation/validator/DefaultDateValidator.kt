package com.nosql.watcher.service.impl.validation.validator

import com.nosql.watcher.dto.ImportWatcherDto
import com.nosql.watcher.service.impl.validation.Validator
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Date
import java.util.regex.Pattern

class DefaultDateValidator: Validator<ImportWatcherDto>() {


    override suspend fun isValid(model: ImportWatcherDto): Boolean {
        if (
            model.date == null || !Pattern.matches(DATE_REGEX, model.date) ||
            dateFormatter.parse(model.date).after(Date.from(Instant.now()))
        ) {
            log.warn("Wrong date for record with id = '${model.id}'")
            return false
        }
        return super.isValid(model);
    }

    companion object {
        private const val DATE_REGEX = "(202\\d)-(0[1-9]|1[0-2])-(0[1-9]|[1-2]\\d|3[0-1])"
        private val dateFormatter = SimpleDateFormat("yyyy-MM-dd")
    }
}