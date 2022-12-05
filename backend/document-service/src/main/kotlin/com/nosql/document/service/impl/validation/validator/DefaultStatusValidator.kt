package com.nosql.document.service.impl.validation.validator

import com.nosql.document.dto.ImportDocumentDto
import com.nosql.document.enumerator.DocumentStatus
import com.nosql.document.service.impl.validation.Validator

class DefaultStatusValidator: Validator<ImportDocumentDto>() {

    override suspend fun isValid(model: ImportDocumentDto): Boolean {
        if (model.status == null || !DocumentStatus.isMember(model.status!!)) {
            log.warn("Wrong status for record with id = '${model.id}'")
            return false
        }
        return super.isValid(model)
    }
}