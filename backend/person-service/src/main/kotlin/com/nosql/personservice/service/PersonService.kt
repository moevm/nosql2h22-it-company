package com.nosql.personservice.service

import com.nosql.personservice.dto.DefaultApiResponseDto
import com.nosql.personservice.dto.PersonDto

interface PersonService {

    suspend fun delete(userId: String): DefaultApiResponseDto

    suspend fun signUp(personDto: PersonDto): DefaultApiResponseDto
}