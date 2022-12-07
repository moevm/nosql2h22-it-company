package com.nosql.personservice.converter

import com.nosql.personservice.dto.export_import.office.ExportOfficeDto
import com.nosql.personservice.entity.OfficeEntity
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class OfficeEntityToExportOfficeDtoConverter: Converter<OfficeEntity, ExportOfficeDto> {

    override fun convert(source: OfficeEntity) = ExportOfficeDto(
        id = source.id!!.toHexString(),
        name = source.name,
        address = source.address,
    )
}