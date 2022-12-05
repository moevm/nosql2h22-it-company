package com.nosql.personservice.service.validation.person.validator

import com.nosql.personservice.dto.export_import.person.ImportPersonDto
import com.nosql.personservice.enumerator.StatusEnum
import com.nosql.personservice.service.validation.Validator

class DefaultStatusValidator: Validator<ImportPersonDto>() {

    override suspend fun isValid(model: ImportPersonDto): Boolean {
        if (model.status == null || !StatusEnum.isMember(model.status!!)) {
            log.warn("Wrong status for record with id = '${model.id}'")
            return false
        }
        return super.isValid(model);
    }
}