package com.nosql.personservice.service.validation.person.validator

import com.nosql.personservice.dto.export_import.person.ImportPersonDto
import com.nosql.personservice.repository.ProjectRepository
import com.nosql.personservice.service.validation.Validator
import kotlinx.coroutines.reactor.awaitSingle
import org.bson.types.ObjectId

class DefaultProjectIdsValidator(
    private val projectRepository: ProjectRepository,
): Validator<ImportPersonDto>() {

    override suspend fun isValid(model: ImportPersonDto): Boolean {
        if (model.confidential!!.projectIds == null || notExistAllProject(model.confidential!!.projectIds!!)) {
            log.warn("Wrong projectIds for record with id = '${model.id}'")
            return false
        }
        return super.isValid(model);
    }

    private suspend fun notExistAllProject(ids: List<String?>): Boolean {
        val projectExistences = ids.map {
            it?. let {
                ObjectId.isValid(it) && projectRepository.existsById(ObjectId(it)).awaitSingle()
            } ?: false
        }

        return if (projectExistences.isNotEmpty()) projectExistences.contains(false) else false
    }
}