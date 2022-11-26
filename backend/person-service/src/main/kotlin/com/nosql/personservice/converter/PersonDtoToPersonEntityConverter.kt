package com.nosql.personservice.converter

import com.nosql.personservice.dto.PersonDto
import com.nosql.personservice.entity.ConfidentialEntity
import com.nosql.personservice.entity.ContactsEntity
import com.nosql.personservice.entity.JobTimeEntity
import com.nosql.personservice.entity.PersonEntity
import com.nosql.personservice.util.convert
import org.bson.types.ObjectId
import org.springframework.context.annotation.Lazy
import org.springframework.core.convert.ConversionService
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class PersonDtoToPersonEntityConverter(
    @Lazy private val conversionService: ConversionService,
) : Converter<PersonDto, PersonEntity> {

    override fun convert(from: PersonDto): PersonEntity {
        return PersonEntity(
            id = ObjectId(from.id),
            name = from.name,
            surname = from.surname,
            patronymic = from.patronymic,
            sex = from.sex,
            birthday = from.birthday,
            firstWorkDate = from.firstWorkDate,
            position = from.position,
            status = from.status,
            contacts = conversionService.convert(from.contacts, ContactsEntity::class),
            jobTime = conversionService.convert(from.jobTime, JobTimeEntity::class),
            officeId = ObjectId(from.officeId),
            confidential = conversionService.convert(from.confidential, ConfidentialEntity::class),
            comment = from.comment,
        )
    }
}