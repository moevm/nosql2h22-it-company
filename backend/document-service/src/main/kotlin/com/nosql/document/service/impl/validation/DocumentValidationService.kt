package com.nosql.document.service.impl.validation

import com.nosql.document.dto.ImportDocumentDto

interface DocumentValidationService {

    suspend fun validate(entity: ImportDocumentDto): Boolean
}
