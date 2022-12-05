package com.nosql.document.service.impl.validation.impl

import com.nosql.document.dto.ImportDocumentDto
import com.nosql.document.service.impl.validation.DefaultValidatorChainBuilder
import com.nosql.document.service.impl.validation.DocumentValidationService
import org.springframework.stereotype.Service

@Service
class DefaultDocumentValidationService(
    private val getDocumentValidatorChain: DefaultValidatorChainBuilder<ImportDocumentDto>
): DocumentValidationService {

    override suspend fun validate(entity: ImportDocumentDto) =
        getDocumentValidatorChain.getFirst()?.isValid(entity) ?: true

}