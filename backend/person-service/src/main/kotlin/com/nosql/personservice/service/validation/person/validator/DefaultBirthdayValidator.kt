package com.nosql.personservice.service.validation.person.validator

import com.nosql.personservice.dto.export_import.person.ImportPersonDto
import com.nosql.personservice.service.validation.Validator
import java.util.regex.Pattern

class DefaultBirthdayValidator: Validator<ImportPersonDto>() {


    override suspend fun isValid(model: ImportPersonDto): Boolean {
        if (model.birthday == null || !Pattern.matches(DATE_REGEX, model.birthday)) {
            log.warn("Wrong birthday for record with id = '${model.id}'")
            return false
        }
        return super.isValid(model);
    }

    companion object {
        private const val DATE_REGEX = "(200[0-4]|19[2-9]{2})-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])"
    }
}