package com.nosql.watcher.service.impl.validation.validator

import com.nosql.watcher.dto.ImportWatcherDto
import com.nosql.watcher.repository.PersonRepository
import com.nosql.watcher.service.impl.validation.Validator
import kotlinx.coroutines.reactor.awaitSingle
import org.bson.types.ObjectId

class DefaultUserIdValidator(
    private val personRepository: PersonRepository
): Validator<ImportWatcherDto>() {

    override suspend fun isValid(model: ImportWatcherDto): Boolean {
        if (
            model.userId == null || !ObjectId.isValid(model.userId!!) ||
            !personRepository.existsById(ObjectId(model.userId)).awaitSingle()
        ) {
            log.warn("Wrong user for record = '${model.id}'")
            return false
        }
        return super.isValid(model);
    }
}