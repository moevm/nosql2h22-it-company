package com.nosql.personservice.service.validation.project.validator

import com.nosql.personservice.dto.export_import.project.ImportProjectDto
import com.nosql.personservice.service.validation.Validator
import java.util.regex.Pattern

class DefaultNameValidator: Validator<ImportProjectDto>() {

    override suspend fun isValid(model: ImportProjectDto): Boolean {
        if (model.name == null || !Pattern.matches(RUSSIAN_ALPHABET_NAME, model.name)) {
            return false
        }
        log.warn("Wrong name for record with id = '${model.id}'")
        return super.isValid(model);
    }

    companion object {
        private const val RUSSIAN_ALPHABET_NAME = "((\\p{InCyrillic}|[A-Za-z])_*-* *,*){1,50}"
    }
}