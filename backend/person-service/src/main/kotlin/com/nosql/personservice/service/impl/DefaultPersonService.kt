package com.nosql.personservice.service.impl

import com.nosql.personservice.component.PersonComponent
import com.nosql.personservice.dto.ContactsDto
import com.nosql.personservice.dto.DefaultApiResponseDto
import com.nosql.personservice.dto.PersonDto
import com.nosql.personservice.entity.ContactsEntity
import com.nosql.personservice.entity.PersonEntity
import com.nosql.personservice.mapper.merge
import com.nosql.personservice.mapper.mergeContacts
import com.nosql.personservice.service.PersonService
import com.nosql.personservice.util.convert
import org.bson.types.ObjectId
import org.springframework.core.convert.ConversionService
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.ZonedDateTime
import java.util.Date

@Service
class DefaultPersonService(
    private val personComponent: PersonComponent,
    private val conversionService: ConversionService,
) : PersonService {

    override suspend fun delete(userId: String): DefaultApiResponseDto {
        val userObjectId = ObjectId(userId)
        personComponent.delete(userObjectId)
        return DefaultApiResponseDto("ok")
    }

    override suspend fun signUp(personDto: PersonDto): DefaultApiResponseDto {
        val person = conversionService.convert(personDto, PersonEntity::class)
        personComponent.save(person)
        return DefaultApiResponseDto("ok")
    }

    override suspend fun get(personId: String): PersonDto {
        val personRecord = personComponent.get(ObjectId(personId))
        return conversionService.convert(personRecord, PersonDto::class)
    }

    override suspend fun getAll(pageable: Pageable) = personComponent.getAll(pageable)
        .map { conversionService.convert(it, PersonDto::class) }

    override suspend fun getAllByName(name: String, pageable: Pageable) = personComponent.getAllByName(name, pageable)
        .map { conversionService.convert(it, PersonDto::class) }

    override suspend fun extendedGet(
        name: String,
        surname: String,
        patronymic: String,
        sex: String,
        position: String,
        status: String,
        startAge: Int,
        endAge: Int,
        pageable: Pageable,
    ) = personComponent.extendedGet(
        name = name,
        surname = surname,
        patronymic = patronymic,
        sex = sex.uppercase(),
        position = position.uppercase(),
        status = status.uppercase(),
        startAge = Date.from(ZonedDateTime.now().minusYears(startAge.toLong()).toInstant()),
        endAge = Date.from(ZonedDateTime.now().minusYears(endAge.toLong()).toInstant()),
        pageable = pageable,
    ).map { conversionService.convert(it, PersonDto::class) }

    override suspend fun updateContacts(personId: String, contactsDto: ContactsDto): PersonDto {
        val personRecord = personComponent.get(ObjectId(personId))
            .apply {
                val contacts = conversionService.convert(contactsDto, ContactsEntity::class)
                mergeContacts(contacts)
            }
            .let { personComponent.update(it) }

        return conversionService.convert(personRecord, PersonDto::class)
    }

    override suspend fun update(personId: String, personDto: PersonDto): PersonDto {
        val personRecord = personComponent.get(ObjectId(personId))
            .apply {
                val person = conversionService.convert(personDto, PersonEntity::class)
                merge(person)
            }
            .let { personComponent.update(it) }

        return conversionService.convert(personRecord, PersonDto::class)
    }
}
