package com.nosql.watcher.service.impl.validation.validator

import com.nosql.watcher.dto.ImportWatcherDto
import com.nosql.watcher.service.impl.validation.Validator
import org.bson.types.ObjectId

class DefaultIdValidator: Validator<ImportWatcherDto>() {

    override suspend fun isValid(model: ImportWatcherDto): Boolean {
        if (model.id == null || !ObjectId.isValid(model.id)) {
            log.warn("Wrong watcher record id = '${model.id}'")
            return false
        }
        return super.isValid(model);
    }
}