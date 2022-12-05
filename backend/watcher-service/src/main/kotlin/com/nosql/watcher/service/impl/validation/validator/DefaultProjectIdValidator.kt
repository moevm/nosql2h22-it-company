package com.nosql.watcher.service.impl.validation.validator

import com.nosql.watcher.dto.ImportWatcherDto
import com.nosql.watcher.repository.ProjectRepository
import com.nosql.watcher.service.impl.validation.Validator
import kotlinx.coroutines.reactor.awaitSingle
import org.bson.types.ObjectId

class DefaultProjectIdValidator(
    private val projectRepository: ProjectRepository,
): Validator<ImportWatcherDto>() {

    override suspend fun isValid(model: ImportWatcherDto): Boolean {
        if (
            model.projectId == null || !ObjectId.isValid(model.projectId) ||
            !projectRepository.existsById(ObjectId(model.projectId)).awaitSingle()
        ) {
            log.warn("Wrong project for record = '${model.id}'")
            return false
        }
        return super.isValid(model);
    }
}