package com.nosql.personservice.service.validation.person.validator

import com.nosql.personservice.dto.export_import.person.ImportPersonDto
import com.nosql.personservice.service.validation.Validator
import java.util.regex.Pattern

class DefaultPatronymicValidator: Validator<ImportPersonDto>() {

    override suspend fun isValid(model: ImportPersonDto): Boolean {
        if (model.patronymic != null && !Pattern.matches(RUSSIAN_ALPHABET_NAME, model.patronymic)) {
            log.warn("Wrong patronymic for record with id = '${model.id}'")
            return false
        }
        return super.isValid(model);
    }

    companion object {
        private const val RUSSIAN_ALPHABET_NAME = "(\\p{InCyrillic}-*){1,40}"
    }
}