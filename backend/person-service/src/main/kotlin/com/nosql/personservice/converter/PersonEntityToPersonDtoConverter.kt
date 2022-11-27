package com.nosql.personservice.converter

import com.nosql.personservice.dto.ConfidentialDto
import com.nosql.personservice.dto.ContactsDto
import com.nosql.personservice.dto.JobTimeDto
import com.nosql.personservice.dto.PersonDto
import com.nosql.personservice.entity.PersonEntity
import com.nosql.personservice.util.convert
import org.springframework.context.annotation.Lazy
import org.springframework.core.convert.ConversionService
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class PersonEntityToPersonDtoConverter(
    @Lazy private val conversionService: ConversionService,
) : Converter<PersonEntity, PersonDto> {

    override fun convert(from: PersonEntity) = PersonDto(
        id = from.id.toHexString(),
        name = from.name,
        surname = from.surname,
        patronymic = from.patronymic,
        sex = from.sex,
        birthday = from.birthday,
        firstWorkDate = from.firstWorkDate,
        position = from.position,
        status = from.status,
        contacts = conversionService.convert(from.contacts, ContactsDto::class),
        jobTime = conversionService.convert(from.jobTime, JobTimeDto::class),
        officeId = from.officeId.toHexString(),
        confidential = conversionService.convert(from.confidential, ConfidentialDto::class),
        comment = from.comment,
    )
}