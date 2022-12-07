package com.nosql.document.service.impl.validation.validator

import com.nosql.document.dto.ImportDocumentDto
import com.nosql.document.repository.PersonRepository
import com.nosql.document.service.impl.validation.Validator
import kotlinx.coroutines.reactor.awaitSingle
import org.bson.types.ObjectId

class DefaultUserIdValidator(
    private val personRepository: PersonRepository,
): Validator<ImportDocumentDto>() {

    override suspend fun isValid(model: ImportDocumentDto): Boolean {
        if (
            model.userId == null || !ObjectId.isValid(model.userId!!) ||
            !personRepository.existsById(ObjectId(model.userId)).awaitSingle()
        ) {
            log.warn("Wrong userId for record = '${model.id}'")
            return false
        }
        return super.isValid(model)
    }
}