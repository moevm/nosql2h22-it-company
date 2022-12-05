package com.nosql.document.service.impl.validation.validator

import com.nosql.document.dto.ImportDocumentDto
import com.nosql.document.enumerator.DocumentType
import com.nosql.document.service.impl.validation.Validator

class DefaultTypeValidator: Validator<ImportDocumentDto>() {

    override suspend fun isValid(model: ImportDocumentDto): Boolean {
        if (model.type == null || !DocumentType.isMember(model.type)) {
            log.warn("Wrong type for record with id = '${model.id}'")
            return false
        }
        return super.isValid(model)
    }
}