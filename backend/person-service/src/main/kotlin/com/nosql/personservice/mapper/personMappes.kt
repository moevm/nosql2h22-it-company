package com.nosql.personservice

import com.nosql.personservice.dto.ContactsDto
import com.nosql.personservice.entity.PersonEntity

fun PersonEntity.mergeContacts(contactsDto: ContactsDto) {
    contacts.email = contactsDto.email
    contacts.phoneNumber = contactsDto.phoneNumber
}