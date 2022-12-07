package com.nosql.personservice.service.validation.project

import com.nosql.personservice.dto.export_import.project.ImportProjectDto

interface ProjectValidationService {

    suspend fun validate(entity: ImportProjectDto): Boolean
}
