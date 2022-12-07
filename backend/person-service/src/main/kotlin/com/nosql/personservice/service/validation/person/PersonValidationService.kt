package com.nosql.personservice.service.validation.person

import com.nosql.personservice.dto.export_import.person.ImportPersonDto

interface PersonValidationService {

    suspend fun validate(entity: ImportPersonDto): Boolean
}