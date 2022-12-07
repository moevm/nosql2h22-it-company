package com.nosql.personservice.converter

import com.nosql.personservice.dto.export_import.office.ImportOfficeDto
import com.nosql.personservice.entity.OfficeEntity
import org.bson.types.ObjectId
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class ImportOfficeDtoToOfficeEntityConverter: Converter<ImportOfficeDto, OfficeEntity> {

    override fun convert(source: ImportOfficeDto) = OfficeEntity(
        id = ObjectId(source.id!!),
        name = source.name!!,
        address = source.address!!,
    )
}