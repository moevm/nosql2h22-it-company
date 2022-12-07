package com.nosql.personservice.service

import com.nosql.personservice.dto.ContactsDto
import com.nosql.personservice.dto.DefaultApiResponseDto
import com.nosql.personservice.dto.PersonDto
import com.nosql.personservice.dto.PersonWithOfficeAndProjectsDto
import org.springframework.data.domain.Pageable

interface PersonService {

    suspend fun delete(userId: String): DefaultApiResponseDto

    suspend fun signUp(personDto: PersonDto): DefaultApiResponseDto

    suspend fun get(personId: String): PersonWithOfficeAndProjectsDto

    suspend fun getAll(pageable: Pageable): List<PersonWithOfficeAndProjectsDto>

    suspend fun getAllByName(name: String, pageable: Pageable): List<PersonWithOfficeAndProjectsDto>

    suspend fun extendedGet(
        name: String,
        surname: String,
        patronymic: String,
        sex: List<String>,
        position: String,
        status: String,
        startAge: Int,
        endAge: Int,
        pageable: Pageable,
    ): List<PersonWithOfficeAndProjectsDto>

    suspend fun updateContacts(personId: String, contactsDto: ContactsDto): PersonWithOfficeAndProjectsDto

    suspend fun update(personId: String, personDto: PersonDto): PersonWithOfficeAndProjectsDto
}