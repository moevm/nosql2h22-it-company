package com.nosql.personservice.converter

import com.nosql.personservice.dto.export_import.person.ImportPassportDataDto
import com.nosql.personservice.entity.PassportDataEntity
import org.springframework.context.annotation.Lazy
import org.springframework.core.convert.ConversionService
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat

@Component
class ImportPassportDataDtoToPassportDataEntityConverter(
    @Lazy private val conversionService: ConversionService,
) : Converter<ImportPassportDataDto, PassportDataEntity> {

    override fun convert(source: ImportPassportDataDto) = PassportDataEntity(
        number = source.number!!,
        issuedDate = dateFormatter.parse(source.issuedDate!!),
        issuedPlace = source.issuedPlace!!
    )

    companion object {
        private val dateFormatter = SimpleDateFormat("yyyy-MM-dd")
    }

}