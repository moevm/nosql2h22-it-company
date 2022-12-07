package com.nosql.personservice.service.validation.person.validator

import com.nosql.personservice.dto.export_import.person.ImportPersonDto
import com.nosql.personservice.service.validation.Validator
import java.util.regex.Pattern

class DefaultConfidentialValidator: Validator<ImportPersonDto>() {


    override suspend fun isValid(model: ImportPersonDto): Boolean {
        if (model.confidential == null || model.confidential!!.salary == null ||
            model.confidential!!.address == null ||
            model.confidential!!.nationality == null ||
            !Pattern.matches(ADDRESS_REGEX, model.confidential!!.address) ||
            !Pattern.matches(NATIONALITY_REGEX, model.confidential!!.nationality) ||
            !Pattern.matches(SALARY_REGEX, model.confidential!!.salary)
        ) {
            log.warn("Wrong confidential for record with id = '${model.id}'")
            return false
        }
        return super.isValid(model)
    }

    companion object {
        private const val ADDRESS_REGEX = "(\\p{InCyrillic}{1,20}\\.* *\\d*,* *){1,5}"
        private const val NATIONALITY_REGEX = "[A-Z-]{2,50}"
        private const val SALARY_REGEX = "[1-9]\\d{1,6}"
    }
}