package com.nosql.personservice.service.impl

import com.nosql.personservice.common.logger.logBefore
import com.nosql.personservice.common.logger.logSuccess
import com.nosql.personservice.common.logger.logger
import com.nosql.personservice.component.OfficeComponent
import com.nosql.personservice.component.PersonComponent
import com.nosql.personservice.component.ProjectComponent
import com.nosql.personservice.dto.export_import.ImportResponseDto
import com.nosql.personservice.dto.export_import.office.ImportOfficeDto
import com.nosql.personservice.dto.export_import.person.ImportPersonDto
import com.nosql.personservice.dto.export_import.project.ImportProjectDto
import com.nosql.personservice.entity.OfficeEntity
import com.nosql.personservice.entity.PersonEntity
import com.nosql.personservice.entity.ProjectEntity
import com.nosql.personservice.service.ImportService
import com.nosql.personservice.service.validation.office.OfficeValidationService
import com.nosql.personservice.service.validation.person.PersonValidationService
import com.nosql.personservice.service.validation.project.ProjectValidationService
import com.nosql.personservice.util.convert
import org.slf4j.Logger
import org.springframework.core.convert.ConversionService
import org.springframework.stereotype.Service

@Service
class DefaultImportService(
    private val conversionService: ConversionService,
    private val personComponent: PersonComponent,
    private val officeComponent: OfficeComponent,
    private val projectComponent: ProjectComponent,
    private val personValidationService: PersonValidationService,
    private val officeValidationService: OfficeValidationService,
    private val projectValidationService: ProjectValidationService,
): ImportService {

    private val log: Logger by logger()

    override suspend fun importPeople(people: List<ImportPersonDto>): ImportResponseDto {

        val expectedCount = people.size
        log.logBefore("Starting importing $expectedCount project records")

        val result = ImportResponseDto(expected = expectedCount)

        people.filter { personValidationService.validate(it) }
            .map { conversionService.convert(it, PersonEntity::class) }
            .let { personComponent.saveAll(it) }
            .also {
                result.actual = it.size
                log.logSuccess("${it.size} person records were imported")
            }

        return result
    }

    override suspend fun importOffices(offices: List<ImportOfficeDto>): ImportResponseDto {

        val expectedCount = offices.size
        log.logBefore("Starting importing $expectedCount project records")

        val result = ImportResponseDto(expected = expectedCount)

        offices.filter { officeValidationService.validate(it) }
            .map { conversionService.convert(it, OfficeEntity::class) }
            .let { officeComponent.saveAll(it) }
            .also {
                result.actual = it.size
                log.logSuccess("${it.size} office records were imported")
            }

        return result
    }

    override suspend fun importProjects(projects: List<ImportProjectDto>): ImportResponseDto {

        val expectedCount = projects.size
        log.logBefore("Starting importing $expectedCount project records")

        val result = ImportResponseDto(expected = expectedCount)

        projects.filter { projectValidationService.validate(it) }
            .map { conversionService.convert(it, ProjectEntity::class) }
            .let { projectComponent.saveAll(it) }
            .also {
                result.actual = it.size
                log.logSuccess("${it.size} project records were imported")
            }

        return result
    }
}
