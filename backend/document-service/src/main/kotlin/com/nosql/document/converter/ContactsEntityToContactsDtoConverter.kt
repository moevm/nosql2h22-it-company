package com.nosql.document.converter

import com.nosql.document.dto.ContactsDto
import com.nosql.document.entity.ContactsEntity
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class ContactsEntityToContactsDtoConverter: Converter<ContactsEntity, ContactsDto> {

    override fun convert(from: ContactsEntity) = ContactsDto(
        phoneNumber = from.phoneNumber,
        email = from.email,
    )
}
