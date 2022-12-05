package com.nosql.personservice.converter

import com.nosql.personservice.dto.export_import.person.ExportJobTimeDto
import com.nosql.personservice.entity.JobTimeEntity
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class JobTimeEntityToExportJobTimeDtoConverter: Converter<JobTimeEntity, ExportJobTimeDto> {

    override fun convert(source: JobTimeEntity) = ExportJobTimeDto(
        start = source.start,
        end = source.end,
    )
}