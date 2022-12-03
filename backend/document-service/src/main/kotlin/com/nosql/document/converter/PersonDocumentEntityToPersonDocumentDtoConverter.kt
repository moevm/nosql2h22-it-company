package com.nosql.document.converter

import com.nosql.document.dto.DocumentDto
import com.nosql.document.dto.PersonDocumentResponseDto
import com.nosql.document.dto.PersonDto
import com.nosql.document.entity.PersonDocumentEntity
import com.nosql.document.util.convert
import org.springframework.context.annotation.Lazy
import org.springframework.core.convert.ConversionService
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class PersonDocumentEntityToPersonDocumentDtoConverter(
    @Lazy private val conversionService: ConversionService,
) : Converter<PersonDocumentEntity, PersonDocumentResponseDto> {

    override fun convert(source: PersonDocumentEntity) = PersonDocumentResponseDto(
        document = conversionService.convert(source.document, DocumentDto::class),
        person = conversionService.convert(source.person, PersonDto::class),
    )
}
