package com.nosql.personservice.converter

import com.nosql.personservice.dto.OfficeDto
import com.nosql.personservice.entity.OfficeEntity
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class OfficeEntityToOfficeDtoConverter : Converter<OfficeEntity, OfficeDto> {

    override fun convert(source: OfficeEntity) = OfficeDto(
        id = source.id!!.toHexString(),
        name = source.name,
        address = source.address,
    )
}