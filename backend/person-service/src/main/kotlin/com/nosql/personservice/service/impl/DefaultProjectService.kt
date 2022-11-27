package com.nosql.personservice.service.impl

import com.nosql.personservice.component.ProjectComponent
import com.nosql.personservice.dto.ProjectDto
import com.nosql.personservice.service.ProjectService
import com.nosql.personservice.util.convert
import org.springframework.core.convert.ConversionService
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class DefaultProjectService(
    private val projectComponent: ProjectComponent,
    private val conversionService: ConversionService,
) : ProjectService {

    override suspend fun getAll(pageable: Pageable) = projectComponent.getAll(pageable)
        .map { conversionService.convert(it, ProjectDto::class) }
}