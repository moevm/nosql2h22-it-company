package com.nosql.personservice.converter

import com.nosql.personservice.dto.export_import.project.ImportProjectDto
import com.nosql.personservice.entity.ProjectEntity
import org.bson.types.ObjectId
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class ImportProjectDtoToProjectEntityConverter: Converter<ImportProjectDto, ProjectEntity> {

    override fun convert(source: ImportProjectDto) = ProjectEntity(
        id = ObjectId(source.id!!),
        name = source.name!!,
    )
}