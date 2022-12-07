package com.nosql.personservice.service.validation.office.validator

import com.nosql.personservice.dto.export_import.office.ImportOfficeDto
import com.nosql.personservice.service.validation.Validator
import java.util.regex.Pattern

class DefaultNameValidator: Validator<ImportOfficeDto>() {

    override suspend fun isValid(model: ImportOfficeDto): Boolean {
        if (model.name == null || !Pattern.matches(RUSSIAN_ALPHABET_NAME, model.name)) {
            log.warn("Wrong name for record with id = '${model.id}'")
            return false
        }
        return super.isValid(model);
    }

    companion object {
        private const val RUSSIAN_ALPHABET_NAME = "(\\p{InCyrillic}-* *,*){1,50}"
    }
}