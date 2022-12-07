package com.nosql.personservice.converter

import com.nosql.personservice.dto.export_import.person.ExportContactsDto
import com.nosql.personservice.entity.ContactsEntity
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class ContactsEntityToExportContactsDtoConverter: Converter<ContactsEntity, ExportContactsDto> {

    override fun convert(source: ContactsEntity) = ExportContactsDto(
        phoneNumber = source.phoneNumber,
        email = source.email,
    )
}