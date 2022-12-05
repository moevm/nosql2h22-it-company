package com.nosql.watcher.service.impl.validation.validator

import com.nosql.watcher.dto.ImportWatcherDto
import com.nosql.watcher.service.impl.validation.Validator

class DefaultCommentValidator: Validator<ImportWatcherDto>() {

    override suspend fun isValid(model: ImportWatcherDto): Boolean {
        if (model.comment == null) {
            log.warn("Wrong comment for record = '${model.id}'")
            return false
        }
        return super.isValid(model);
    }
}