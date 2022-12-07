package com.nosql.personservice.service.validation.person.impl

import com.nosql.personservice.dto.export_import.person.ImportPersonDto
import com.nosql.personservice.service.validation.person.PersonValidationService
import com.nosql.personservice.service.validation.DefaultValidatorChainBuilder
import org.springframework.stereotype.Service

@Service
class DefaultPersonValidationService(
    private val getPersonValidatorChain: DefaultValidatorChainBuilder<ImportPersonDto>
): PersonValidationService {

    override suspend fun validate(entity: ImportPersonDto) =
        getPersonValidatorChain.getFirst()?.isValid(entity) ?: true

}