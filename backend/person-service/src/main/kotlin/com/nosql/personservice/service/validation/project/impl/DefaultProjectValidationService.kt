package com.nosql.personservice.service.validation.project.impl

import com.nosql.personservice.dto.export_import.project.ImportProjectDto
import com.nosql.personservice.service.validation.DefaultValidatorChainBuilder
import com.nosql.personservice.service.validation.project.ProjectValidationService
import org.springframework.stereotype.Service

@Service
class DefaultProjectValidationService(
    private val getProjectValidatorChain: DefaultValidatorChainBuilder<ImportProjectDto>
): ProjectValidationService {

    override suspend fun validate(entity: ImportProjectDto) =
        getProjectValidatorChain.getFirst()?.isValid(entity) ?: true

}