package com.nosql.personservice.converter

import com.nosql.personservice.dto.export_import.person.ExportConfidentialDto
import com.nosql.personservice.dto.export_import.person.ExportContactsDto
import com.nosql.personservice.dto.export_import.person.ExportJobTimeDto
import com.nosql.personservice.dto.export_import.person.ExportPersonDto
import com.nosql.personservice.entity.PersonEntity
import com.nosql.personservice.util.convert
import org.springframework.context.annotation.Lazy
import org.springframework.core.convert.ConversionService
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class PersonEntityToExportPersonDtoConverter(
    @Lazy private val conversionService: ConversionService,
): Converter<PersonEntity, ExportPersonDto> {

    override fun convert(source: PersonEntity) = ExportPersonDto(
        id = source.id.toHexString(),
        name = source.name,
        surname = source.surname,
        patronymic = source.patronymic,
        sex = source.sex,
        birthday = source.birthday,
        firstWorkDate = source.firstWorkDate,
        position = source.position,
        status = source.status,
        contacts = conversionService.convert(source.contacts, ExportContactsDto::class),
        jobTime = conversionService.convert(source.jobTime, ExportJobTimeDto::class),
        officeId = source.officeId.toHexString(),
        confidential = conversionService.convert(source.confidential, ExportConfidentialDto::class),
        comment = source.comment,
    )
}