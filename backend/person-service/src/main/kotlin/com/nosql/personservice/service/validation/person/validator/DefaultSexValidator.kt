package com.nosql.personservice.service.validation.person.validator

import com.nosql.personservice.dto.export_import.person.ImportPersonDto
import com.nosql.personservice.enumerator.SexEnum
import com.nosql.personservice.service.validation.Validator

class DefaultSexValidator: Validator<ImportPersonDto>() {

    override suspend fun isValid(model: ImportPersonDto): Boolean {
        if (model.sex == null || !SexEnum.isMember(model.sex!!)) {
            log.warn("Wrong sex for record with id = '${model.id}'")
            return false
        }
        return super.isValid(model);
    }
}