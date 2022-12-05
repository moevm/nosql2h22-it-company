package com.nosql.document.service.impl.validation.validator

import com.nosql.document.dto.ImportDocumentDto
import com.nosql.document.service.impl.validation.Validator
import org.bson.types.ObjectId

class DefaultIdValidator: Validator<ImportDocumentDto>() {

    override suspend fun isValid(model: ImportDocumentDto): Boolean {
        if (model.id == null || !ObjectId.isValid(model.id)) {
            log.warn("Wrong document record id = '${model.id}'")
            return false
        }
        return super.isValid(model)
    }
}