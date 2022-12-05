package com.nosql.personservice.service.validation.person.validator

import com.nosql.personservice.dto.export_import.person.ImportPersonDto
import com.nosql.personservice.service.validation.Validator
import java.util.regex.Pattern

class DefaultFirstWorkDayValidator: Validator<ImportPersonDto>() {


    override suspend fun isValid(model: ImportPersonDto): Boolean {
        if (model.firstWorkDate == null || !Pattern.matches(DATE_REGEX, model.firstWorkDate)) {
            log.warn("Wrong firstWorkDay for record with id = '${model.id}'")
            return false
        }
        return super.isValid(model);
    }

    companion object {
        private const val DATE_REGEX = "(20(1\\d|2[0-2]))-(0[1-9]|1[0-2])-(0[1-9]|[1-2]\\d|3[0-1])"
    }
}