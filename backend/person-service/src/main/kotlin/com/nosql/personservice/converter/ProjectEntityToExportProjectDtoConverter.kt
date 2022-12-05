package com.nosql.personservice.converter

import com.nosql.personservice.dto.export_import.project.ExportProjectDto
import com.nosql.personservice.entity.ProjectEntity
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class ProjectEntityToExportProjectDtoConverter: Converter<ProjectEntity, ExportProjectDto> {

    override fun convert(source: ProjectEntity) = ExportProjectDto(
        id = source.id!!.toHexString(),
        name = source.name,
    )
}