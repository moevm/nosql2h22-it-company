package com.nosql.personservice.converter

import com.nosql.personservice.dto.PassportDataDto
import com.nosql.personservice.entity.PassportDataEntity
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class PassportDataDtoToPassportDataEntityConverter : Converter<PassportDataDto, PassportDataEntity> {

    override fun convert(from: PassportDataDto): PassportDataEntity {
        return PassportDataEntity(
            number = from.number,
            issuedPlace = from.issuedPlace,
            issuedDate = from.issuedDate,
        )
    }
}