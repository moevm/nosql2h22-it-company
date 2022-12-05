package com.nosql.personservice.service.validation.person.validator

import com.nosql.personservice.dto.export_import.person.ImportPersonDto
import com.nosql.personservice.enumerator.PositionEnum
import com.nosql.personservice.service.validation.Validator

class DefaultPositionValidator: Validator<ImportPersonDto>() {

    override suspend fun isValid(model: ImportPersonDto): Boolean {
        if (model.position == null || !PositionEnum.isMember(model.position!!)) {
            log.warn("Wrong position for record with id = '${model.id}'")
            return false
        }
        return super.isValid(model);
    }
}