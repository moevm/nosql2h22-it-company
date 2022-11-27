package com.nosql.personservice.converter;

import com.nosql.personservice.dto.ContactsDto;
import com.nosql.personservice.entity.ContactsEntity;
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component;

@Component
class ContactsEntityToContactsDtoConverter: Converter<ContactsEntity, ContactsDto> {

    override fun convert(from: ContactsEntity) = ContactsDto(
        phoneNumber = from.phoneNumber,
        email = from.email,
    )
}
