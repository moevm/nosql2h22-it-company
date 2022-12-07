package com.nosql.personservice.converter

import com.nosql.personservice.dto.export_import.person.ImportConfidentialDto
import com.nosql.personservice.entity.ConfidentialEntity
import com.nosql.personservice.entity.PassportDataEntity
import com.nosql.personservice.util.convert
import org.bson.types.ObjectId
import org.springframework.context.annotation.Lazy
import org.springframework.core.convert.ConversionService
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class ImportConfidentialDtoToConfidentialEntityConverter(
    @Lazy private val conversionService: ConversionService,
) : Converter<ImportConfidentialDto, ConfidentialEntity> {

    override fun convert(source: ImportConfidentialDto) = ConfidentialEntity(
        passportData = conversionService.convert(source.passportData!!, PassportDataEntity::class),
        salary = source.salary!!.toInt(),
        address = source.address!!,
        nationality = source.nationality!!,
        projectIds = source.projectIds!!.map { ObjectId(it) }
    )

}