package com.nosql.watcher.service.impl.validation.impl

import com.nosql.watcher.dto.ImportWatcherDto
import com.nosql.watcher.service.impl.validation.DefaultValidatorChainBuilder
import com.nosql.watcher.service.impl.validation.WatcherValidationService
import org.springframework.stereotype.Service

@Service
class DefaultWatcherValidationService(
    private val getDocumentValidatorChain: DefaultValidatorChainBuilder<ImportWatcherDto>
): WatcherValidationService {

    override suspend fun validate(entity: ImportWatcherDto) =
        getDocumentValidatorChain.getFirst()?.isValid(entity) ?: true

}