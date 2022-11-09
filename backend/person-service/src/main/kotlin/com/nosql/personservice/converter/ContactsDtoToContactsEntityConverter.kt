package com.nosql.personservice.converter

import com.nosql.personservice.dto.ContactsDto
import com.nosql.personservice.entity.ContactsEntity
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class ContactsDtoToContactsEntityConverter : Converter<ContactsDto, ContactsEntity> {

    override fun convert(from: ContactsDto): ContactsEntity {
        return ContactsEntity(
            phoneNumber = from.phoneNumber,
            email = from.email,
        )
    }
}