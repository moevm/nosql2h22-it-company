package com.nosql.personservice.service.validation.person.validator

import com.nosql.personservice.dto.export_import.person.ImportPersonDto
import com.nosql.personservice.service.validation.Validator
import java.util.regex.Pattern

class DefaultJobTimeValidator: Validator<ImportPersonDto>() {


    override suspend fun isValid(model: ImportPersonDto): Boolean {
        if (
            model.jobTime == null || model.jobTime!!.start == null || model.jobTime!!.end == null ||
            !Pattern.matches(TIME_REGEX, model.jobTime!!.start) || !Pattern.matches(TIME_REGEX, model.jobTime!!.end)
        ) {
            log.warn("Wrong jobTime for record with id = '${model.id}'")
            return false
        }
        return super.isValid(model);
    }

    companion object {
        private const val TIME_REGEX = "([0-1]\\d|2[0-3]):([0-5]\\d)"
    }
}