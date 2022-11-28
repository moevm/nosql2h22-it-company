package com.nosql.document.converter

import com.nosql.document.dto.DocumentDto
import com.nosql.document.entity.DocumentEntity
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class DocumentEntityToDocumentDtoConverter : Converter<DocumentEntity, DocumentDto> {

    override fun convert(source: DocumentEntity) = DocumentDto(
        id = source.id!!.toHexString(),
        type = source.type,
        orderDate = source.orderDate,
        status = source.status,
    )
}