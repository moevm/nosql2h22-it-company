package com.nosql.watcher.service.impl.validation

import com.nosql.watcher.dto.ImportWatcherDto

interface WatcherValidationService {

    suspend fun validate(entity: ImportWatcherDto): Boolean
}
