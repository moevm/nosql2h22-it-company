package com.nosql.personservice.converter

import com.nosql.personservice.dto.ConfidentialDto
import com.nosql.personservice.dto.PassportDataDto
import com.nosql.personservice.entity.ConfidentialEntity
import com.nosql.personservice.util.convert
import org.springframework.context.annotation.Lazy
import org.springframework.core.convert.ConversionService
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class ConfidentialEntityToConfidentialDtoConverter(
    @Lazy private val conversionService: ConversionService,
) : Converter<ConfidentialEntity, ConfidentialDto> {

    override fun convert(from: ConfidentialEntity) = ConfidentialDto(
        passportData = conversionService.convert(from.passportData, PassportDataDto::class),
        nationality = from.nationality,
        address = from.address,
        salary = from.salary,
        projectIds = from.projectIds.map { it.toHexString() }
    )
}