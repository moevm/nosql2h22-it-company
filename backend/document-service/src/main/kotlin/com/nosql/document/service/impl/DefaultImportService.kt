package com.nosql.document.service.impl

import com.nosql.document.common.logger.logBefore
import com.nosql.document.common.logger.logSuccess
import com.nosql.document.common.logger.logger
import com.nosql.document.component.DocumentComponent
import com.nosql.document.dto.ImportDocumentDto
import com.nosql.document.dto.ImportResponseDto
import com.nosql.document.entity.DocumentEntity
import com.nosql.document.service.ImportService
import com.nosql.document.service.impl.validation.DocumentValidationService
import com.nosql.document.util.convert
import org.slf4j.Logger
import org.springframework.core.convert.ConversionService
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class DefaultImportService(
    private val conversionService: ConversionService,
    private val documentComponent: DocumentComponent,
    private val documentValidationService: DocumentValidationService,
): ImportService {

    private val log: Logger by logger()

    override suspend fun importDocuments(documents: List<ImportDocumentDto>): ImportResponseDto {

        val expectedCount = documents.size
        log.logBefore("Starting importing $expectedCount document records")

        val result = ImportResponseDto(expected = expectedCount)

        documents.filter { documentValidationService.validate(it) }
            .map {
                it.completeDate = it.completeDate?.let { e -> LocalDate.parse(e).plusDays(1).toString() }
                it.orderDate =  LocalDate.parse(it.orderDate).plusDays(1).toString()
                conversionService.convert(it, DocumentEntity::class)
            }
            .let { documentComponent.saveAll(it) }
            .also {
                result.actual = it.size
                log.logSuccess("${it.size} document records were imported")
            }

        return result
    }
}
