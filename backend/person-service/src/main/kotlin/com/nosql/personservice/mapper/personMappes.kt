package com.nosql.personservice.mapper

import com.nosql.personservice.entity.ContactsEntity
import com.nosql.personservice.entity.PersonEntity

fun PersonEntity.mergeContacts(contactsDto: ContactsEntity) {
    contacts.email = contactsDto.email
    contacts.phoneNumber = contactsDto.phoneNumber
}

fun PersonEntity.merge(person: PersonEntity) {
    name = person.name
    surname = person.surname
    patronymic = person.patronymic
    sex = person.sex
    birthday = person.birthday
    firstWorkDate = person.firstWorkDate
    position = person.position
    status = person.status
    confidential.passportData.number = person.confidential.passportData.number
    confidential.passportData.issuedDate = person.confidential.passportData.issuedDate
    confidential.passportData.issuedPlace = person.confidential.passportData.issuedPlace
    confidential.address = person.confidential.address
    confidential.nationality = person.confidential.nationality
    confidential.salary = person.confidential.salary
    confidential.projectIds = person.confidential.projectIds
    jobTime.start = person.jobTime.start
    jobTime.end = person.jobTime.end
    officeId = person.officeId
    contacts.email = person.contacts.email
    contacts.phoneNumber = person.contacts.phoneNumber
    comment = person.comment
}