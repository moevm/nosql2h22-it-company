package com.nosql.personservice.service

import com.nosql.personservice.dto.ContactsDto
import com.nosql.personservice.dto.DefaultApiResponseDto
import com.nosql.personservice.dto.PersonDto
import org.springframework.data.domain.Pageable

interface PersonService {

    suspend fun delete(userId: String): DefaultApiResponseDto

    suspend fun signUp(personDto: PersonDto): DefaultApiResponseDto

    suspend fun get(personId: String): PersonDto

    suspend fun getAll(pageable: Pageable): List<PersonDto>

    suspend fun getAllByName(name: String, pageable: Pageable): List<PersonDto>

    suspend fun extendedGet(
        name: String,
        surname: String,
        patronymic: String,
        sex: String,
        position: String,
        status: String,
        startAge: Int,
        endAge: Int,
        pageable: Pageable,
    ): List<PersonDto>

    suspend fun editContacts(personId: String, contactsDto: ContactsDto): PersonDto
}