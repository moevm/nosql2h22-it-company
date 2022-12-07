package com.nosql.personservice.converter

import com.nosql.personservice.dto.export_import.person.ExportPassportDataDto
import com.nosql.personservice.entity.PassportDataEntity
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class PassportDataEntityToExportPassportDataDtoConverter: Converter<PassportDataEntity, ExportPassportDataDto> {

    override fun convert(source: PassportDataEntity) = ExportPassportDataDto(
        number = source.number,
        issuedPlace = source.issuedPlace,
        issuedDate = source.issuedDate,
    )
}