package com.nosql.personservice.converter

import com.nosql.personservice.dto.export_import.person.ImportContactsDto
import com.nosql.personservice.entity.ContactsEntity
import org.springframework.context.annotation.Lazy
import org.springframework.core.convert.ConversionService
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class ImportContactsDtoToContactsEntityConverter(
    @Lazy private val conversionService: ConversionService,
) : Converter<ImportContactsDto, ContactsEntity> {

    override fun convert(source: ImportContactsDto) = ContactsEntity(
        email = source.email!!,
        phoneNumber = source.phoneNumber!!
    )

}