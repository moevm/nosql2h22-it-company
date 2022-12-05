package com.nosql.personservice.service.validation.person.validator

import com.nosql.personservice.dto.export_import.person.ImportPersonDto
import com.nosql.personservice.repository.OfficeRepository
import com.nosql.personservice.service.validation.Validator
import kotlinx.coroutines.reactor.awaitSingle
import org.bson.types.ObjectId

class DefaultOfficeValidator(
    private val officeRepository: OfficeRepository,
): Validator<ImportPersonDto>() {

    override suspend fun isValid(model: ImportPersonDto): Boolean {
        if (
            model.officeId == null || !ObjectId.isValid(model.officeId) ||
            !officeRepository.existsById(ObjectId(model.officeId)).awaitSingle()
        ) {
            log.warn("Wrong officeId for record with id = '${model.id}'")
            return false
        }
        return super.isValid(model);
    }
}