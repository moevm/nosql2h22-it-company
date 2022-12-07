package com.nosql.watcher.service.impl.validation.validator

import com.nosql.watcher.dto.ImportWatcherDto
import com.nosql.watcher.service.impl.validation.Validator
import java.util.regex.Pattern

class DefaultMinutesAmountValidator: Validator<ImportWatcherDto>() {


    override suspend fun isValid(model: ImportWatcherDto): Boolean {
        if (model.minutesAmount == null ||
            !Pattern.matches(SALARY_REGEX, model.minutesAmount)
        ) {
            log.warn("Wrong minutesAmount for record with id = '${model.id}'")
            return false
        }
        return super.isValid(model)
    }

    companion object {
        private const val SALARY_REGEX = "[1-9]\\d{2,8}"
    }
}