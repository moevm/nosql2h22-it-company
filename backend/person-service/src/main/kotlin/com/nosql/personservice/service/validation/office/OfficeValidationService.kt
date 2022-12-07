package com.nosql.personservice.service.validation.office

import com.nosql.personservice.dto.export_import.office.ImportOfficeDto

interface OfficeValidationService {

    suspend fun validate(entity: ImportOfficeDto): Boolean
}
