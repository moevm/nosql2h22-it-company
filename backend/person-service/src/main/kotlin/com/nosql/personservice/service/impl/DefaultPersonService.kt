package com.nosql.personservice.service.impl

import com.nosql.personservice.component.impl.DefaultPersonComponent
import com.nosql.personservice.dto.DefaultApiResponseDto
import com.nosql.personservice.dto.PersonDto
import com.nosql.personservice.entity.PersonEntity
import com.nosql.personservice.service.PersonService
import com.nosql.personservice.util.convert
import org.bson.types.ObjectId
import org.springframework.core.convert.ConversionService
import org.springframework.stereotype.Service

@Service
class DefaultPersonService(
    private val personComponent: DefaultPersonComponent,
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
}