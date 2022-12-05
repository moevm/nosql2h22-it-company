package com.nosql.personservice.converter

import com.nosql.personservice.dto.export_import.person.ExportConfidentialDto
import com.nosql.personservice.dto.export_import.person.ExportPassportDataDto
import com.nosql.personservice.entity.ConfidentialEntity
import com.nosql.personservice.util.convert
import org.springframework.context.annotation.Lazy
import org.springframework.core.convert.ConversionService
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class ConfidentialEntityToExportConfidentialDtoConverter(
    @Lazy private val conversionService: ConversionService,
): Converter<ConfidentialEntity, ExportConfidentialDto> {

    override fun convert(source: ConfidentialEntity) = ExportConfidentialDto(
        passportData = conversionService.convert(source.passportData, ExportPassportDataDto::class),
        nationality = source.nationality,
        address = source.address,
        salary = source.salary,
        projectIds = source.projectIds.map { it.toHexString() }
    )
}