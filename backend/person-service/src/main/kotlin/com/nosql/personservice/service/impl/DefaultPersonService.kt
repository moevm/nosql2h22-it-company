package com.nosql.personservice.service.impl

import com.nosql.personservice.component.OfficeComponent
import com.nosql.personservice.component.PersonComponent
import com.nosql.personservice.component.ProjectComponent
import com.nosql.personservice.dto.ContactsDto
import com.nosql.personservice.dto.DefaultApiResponseDto
import com.nosql.personservice.dto.OfficeDto
import com.nosql.personservice.dto.PersonDto
import com.nosql.personservice.dto.PersonWithOfficeAndProjectsDto
import com.nosql.personservice.dto.ProjectDto
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
    private val officeComponent: OfficeComponent,
    private val projectComponent: ProjectComponent,
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

    override suspend fun get(personId: String): PersonWithOfficeAndProjectsDto {
        val person = personComponent.get(ObjectId(personId))
        return convertToPersonWithOfficeAndProjectsDto(person)
    }

    override suspend fun getAll(pageable: Pageable) = personComponent.getAll(pageable)
        .map { convertToPersonWithOfficeAndProjectsDto(it) }

    override suspend fun getAllByName(name: String, pageable: Pageable) = personComponent.getAllByName(name, pageable)
        .map { convertToPersonWithOfficeAndProjectsDto(it) }

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
    ).map { convertToPersonWithOfficeAndProjectsDto(it) }

    override suspend fun updateContacts(personId: String, contactsDto: ContactsDto): PersonWithOfficeAndProjectsDto {
        val person = personComponent.get(ObjectId(personId))
            .apply {
                val contacts = conversionService.convert(contactsDto, ContactsEntity::class)
                mergeContacts(contacts)
            }
            .let { personComponent.update(it) }

        return convertToPersonWithOfficeAndProjectsDto(person)
    }

    override suspend fun update(personId: String, personDto: PersonDto): PersonWithOfficeAndProjectsDto {
        val person = personComponent.get(ObjectId(personId))
            .apply {
                val person = conversionService.convert(personDto, PersonEntity::class)
                merge(person)
            }
            .let { personComponent.update(it) }

        return convertToPersonWithOfficeAndProjectsDto(person)
    }

    private suspend fun convertToPersonWithOfficeAndProjectsDto(
        person: PersonEntity,
    ): PersonWithOfficeAndProjectsDto {
        val officeId = person.officeId
        val projectIds = person.confidential.projectIds
        val personWithOfficeAndProjects = conversionService.convert(person, PersonWithOfficeAndProjectsDto::class)
        personWithOfficeAndProjects.office =
            officeComponent.getById(officeId).let { conversionService.convert(it, OfficeDto::class) }
        personWithOfficeAndProjects.confidential.projects = projectComponent.getAllByIds(projectIds)
            .map { conversionService.convert(it, ProjectDto::class) }
        return personWithOfficeAndProjects
    }
}
