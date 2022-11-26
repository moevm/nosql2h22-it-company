package com.nosql.personservice.converter

import com.nosql.personservice.dto.ConfidentialDto
import com.nosql.personservice.entity.ConfidentialEntity
import com.nosql.personservice.entity.PassportDataEntity
import com.nosql.personservice.util.convert
import org.bson.types.ObjectId
import org.springframework.context.annotation.Lazy
import org.springframework.core.convert.ConversionService
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class ConfidentialDtoToConfidentialEntityConverter(
    @Lazy private val conversionService: ConversionService,
) : Converter<ConfidentialDto, ConfidentialEntity> {

    override fun convert(from: ConfidentialDto): ConfidentialEntity {
        return ConfidentialEntity(
            passportData = conversionService.convert(from.passportData, PassportDataEntity::class),
            nationality = from.nationality,
            address = from.address,
            salary = from.salary,
            projectIds = from.projectIds.map { ObjectId(it) }
        )
    }
}