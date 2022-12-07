package com.nosql.personservice.service.validation.office.impl

import com.nosql.personservice.dto.export_import.office.ImportOfficeDto
import com.nosql.personservice.service.validation.DefaultValidatorChainBuilder
import com.nosql.personservice.service.validation.office.OfficeValidationService
import org.springframework.stereotype.Service

@Service
class DefaultOfficeValidationService(
    private val getOfficeValidatorChain: DefaultValidatorChainBuilder<ImportOfficeDto>
): OfficeValidationService {

    override suspend fun validate(entity: ImportOfficeDto) =
        getOfficeValidatorChain.getFirst()?.isValid(entity) ?: true

}