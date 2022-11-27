package com.nosql.personservice.converter

import com.nosql.personservice.dto.ProjectDto
import com.nosql.personservice.entity.ProjectEntity
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class ProjectEntityToProjectDtoConverter : Converter<ProjectEntity, ProjectDto> {

    override fun convert(source: ProjectEntity) = ProjectDto(
        id = source.id!!.toHexString(),
        name = source.name,
    )
}