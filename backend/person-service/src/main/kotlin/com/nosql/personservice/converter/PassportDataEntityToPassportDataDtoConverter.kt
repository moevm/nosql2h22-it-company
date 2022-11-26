package com.nosql.personservice.converter

import com.nosql.personservice.dto.PassportDataDto
import com.nosql.personservice.entity.PassportDataEntity
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class PassportDataEntityToPassportDataDtoConverter : Converter<PassportDataEntity, PassportDataDto> {

    override fun convert(from: PassportDataEntity): PassportDataDto {
        return PassportDataDto(
            number = from.number,
            issuedPlace = from.issuedPlace,
            issuedDate = from.issuedDate,
        )
    }
}