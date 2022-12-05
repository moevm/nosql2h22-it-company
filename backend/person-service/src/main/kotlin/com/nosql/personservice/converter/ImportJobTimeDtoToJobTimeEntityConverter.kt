package com.nosql.personservice.converter

import com.nosql.personservice.dto.export_import.person.ImportJobTimeDto
import com.nosql.personservice.entity.JobTimeEntity
import org.springframework.context.annotation.Lazy
import org.springframework.core.convert.ConversionService
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class ImportJobTimeDtoToJobTimeEntityConverter(
    @Lazy private val conversionService: ConversionService,
) : Converter<ImportJobTimeDto, JobTimeEntity> {

    override fun convert(source: ImportJobTimeDto) = JobTimeEntity(
        start = source.start!!,
        end = source.end!!,
    )

}