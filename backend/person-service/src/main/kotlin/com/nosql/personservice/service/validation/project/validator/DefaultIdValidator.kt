package com.nosql.personservice.service.validation.project.validator

import com.nosql.personservice.dto.export_import.project.ImportProjectDto
import com.nosql.personservice.service.validation.Validator
import org.bson.types.ObjectId

class DefaultIdValidator: Validator<ImportProjectDto>() {

    override suspend fun isValid(model: ImportProjectDto): Boolean {
        if (model.id == null || !ObjectId.isValid(model.id)) {
            return false
        }
        log.warn("Wrong project record id = '${model.id}'")
        return super.isValid(model);
    }
}