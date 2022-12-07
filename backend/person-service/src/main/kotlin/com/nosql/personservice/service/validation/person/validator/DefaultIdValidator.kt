package com.nosql.personservice.service.validation.person.validator

import com.nosql.personservice.dto.export_import.person.ImportPersonDto
import com.nosql.personservice.repository.UserRepository
import com.nosql.personservice.service.validation.Validator
import kotlinx.coroutines.reactor.awaitSingle
import org.bson.types.ObjectId

class DefaultIdValidator(
    private val userRepository: UserRepository,
): Validator<ImportPersonDto>() {

    override suspend fun isValid(model: ImportPersonDto): Boolean {
        if (
            model.id == null || !ObjectId.isValid(model.id!!) || !userRepository.existsById(ObjectId(model.id)).awaitSingle()
        ) {
            log.warn("Wrong id for record = '${model.id}'")
            return false
        }
        return super.isValid(model);
    }
}