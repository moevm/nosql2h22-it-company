package com.nosql.personservice.service.validation.office.validator

import com.nosql.personservice.dto.export_import.office.ImportOfficeDto
import com.nosql.personservice.service.validation.Validator
import java.util.regex.Pattern

class DefaultAddressValidator: Validator<ImportOfficeDto>() {

    override suspend fun isValid(model: ImportOfficeDto): Boolean {
        if (model.name == null || !Pattern.matches(ADDRESS_REGEX, model.address)) {
            log.warn("Wrong address for record with id = '${model.id}'")
            return false
        }
        return super.isValid(model);
    }

    companion object {
        private const val ADDRESS_REGEX = "((\\p{InCyrillic}{1,20}-*\\.* *\\d*,* *){1,10}){1,100}"
    }
}