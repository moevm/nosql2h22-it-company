package com.nosql.personservice.service.validation.person.validator

import com.nosql.personservice.dto.export_import.person.ImportPersonDto
import com.nosql.personservice.service.validation.Validator
import java.util.regex.Pattern

class DefaultNameValidator: Validator<ImportPersonDto>() {

    override suspend fun isValid(model: ImportPersonDto): Boolean {
        if (model.name == null || !Pattern.matches(RUSSIAN_ALPHABET_NAME, model.name)) {
            return false
        }
        log.warn("Wrong name for record with id = '${model.id}'")
        return super.isValid(model);
    }

    companion object {
        private const val RUSSIAN_ALPHABET_NAME = "(\\p{InCyrillic}-*){1,40}"
    }
}