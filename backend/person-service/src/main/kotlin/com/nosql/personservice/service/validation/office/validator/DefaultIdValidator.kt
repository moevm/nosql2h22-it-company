package com.nosql.personservice.service.validation.office.validator

import com.nosql.personservice.dto.export_import.office.ImportOfficeDto
import com.nosql.personservice.service.validation.Validator
import org.bson.types.ObjectId

class DefaultIdValidator: Validator<ImportOfficeDto>()  {

    override suspend fun isValid(model: ImportOfficeDto): Boolean {
        if (model.id == null || !ObjectId.isValid(model.id)) {
            log.warn("Wrong office record id = '${model.id}'")
            return false
        }
        return super.isValid(model);
    }
}