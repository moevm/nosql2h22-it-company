package com.nosql.personservice.converter

import com.nosql.personservice.dto.ConfidentialWithProjectDto
import com.nosql.personservice.dto.PassportDataDto
import com.nosql.personservice.entity.ConfidentialEntity
import com.nosql.personservice.repository.ProjectRepository
import com.nosql.personservice.util.convert
import org.springframework.context.annotation.Lazy
import org.springframework.core.convert.ConversionService
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class ConfidentialEntityToConfidentialWithProjectDtoConverter(
    @Lazy private val conversionService: ConversionService,
    private val projectRepository: ProjectRepository,
) : Converter<ConfidentialEntity, ConfidentialWithProjectDto> {

    override fun convert(from: ConfidentialEntity) =
        ConfidentialWithProjectDto(
            passportData = conversionService.convert(from.passportData, PassportDataDto::class),
            nationality = from.nationality,
            address = from.address,
            salary = from.salary,
        )
}