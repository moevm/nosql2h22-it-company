package com.nosql.document.converter

import com.nosql.document.dto.ContactsDto
import com.nosql.document.dto.PersonDto
import com.nosql.document.entity.PersonEntity
import com.nosql.document.util.convert
import org.springframework.context.annotation.Lazy
import org.springframework.core.convert.ConversionService
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class PersonEntityToPersonDtoConverter(
    @Lazy private val conversionService: ConversionService,
) : Converter<PersonEntity, PersonDto> {

    override fun convert(source: PersonEntity) = PersonDto(
        name = source.name,
        surname = source.surname,
        contacts = conversionService.convert(source.contacts, ContactsDto::class)
    )
}