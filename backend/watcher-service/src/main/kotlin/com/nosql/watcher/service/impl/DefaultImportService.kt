package com.nosql.watcher.service.impl

import com.nosql.watcher.common.logger.logBefore
import com.nosql.watcher.common.logger.logSuccess
import com.nosql.watcher.common.logger.logger
import com.nosql.watcher.component.WatcherComponent
import com.nosql.watcher.dto.ImportResponseDto
import com.nosql.watcher.dto.ImportWatcherDto
import com.nosql.watcher.entity.WatcherEntity
import com.nosql.watcher.service.ImportService
import com.nosql.watcher.service.impl.validation.WatcherValidationService
import com.nosql.watcher.util.convert
import org.slf4j.Logger
import org.springframework.core.convert.ConversionService
import org.springframework.stereotype.Service

@Service
class DefaultImportService(
    private val conversionService: ConversionService,
    private val watcherComponent: WatcherComponent,
    private val watcherValidationService: WatcherValidationService,
): ImportService {

    private val log: Logger by logger()

    override suspend fun importDocuments(watchers: List<ImportWatcherDto>): ImportResponseDto {

        val expectedCount = watchers.size
        log.logBefore("Starting importing $expectedCount watcher records")

        val result = ImportResponseDto(expected = expectedCount)

        watchers.filter { watcherValidationService.validate(it) }
            .map { conversionService.convert(it, WatcherEntity::class) }
            .let { watcherComponent.saveAll(it) }
            .also {
                result.actual = it.size
                log.logSuccess("${it.size} watcher records were imported")
            }

        return result
    }
}
