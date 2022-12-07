package com.nosql.personservice.service.validation.person.validator

import com.nosql.personservice.dto.export_import.person.ImportPersonDto
import com.nosql.personservice.service.validation.Validator
import java.util.regex.Pattern

class DefaultSurnameValidator: Validator<ImportPersonDto>() {

    override suspend fun isValid(model: ImportPersonDto): Boolean {
        if (model.surname == null || !Pattern.matches(RUSSIAN_ALPHABET_SURNAME, model.surname)) {
            log.warn("Wrong surname for record with id = '${model.id}'")
            return false
        }
        return super.isValid(model);
    }

    companion object {
        private const val RUSSIAN_ALPHABET_SURNAME = "(\\p{InCyrillic}-*){1,40}"
    }
}