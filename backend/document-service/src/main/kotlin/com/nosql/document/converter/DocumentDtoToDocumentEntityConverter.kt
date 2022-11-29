package com.nosql.document.converter

import com.nosql.document.dto.DocumentDto
import com.nosql.document.entity.DocumentEntity
import org.bson.types.ObjectId
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class DocumentDtoToDocumentEntityConverter : Converter<DocumentDto, DocumentEntity> {

    override fun convert(source: DocumentDto) = DocumentEntity(
        id = source.id?.let { ObjectId(it) },
        type = source.type,
        orderDate = source.orderDate,
        status = source.status
    )
}