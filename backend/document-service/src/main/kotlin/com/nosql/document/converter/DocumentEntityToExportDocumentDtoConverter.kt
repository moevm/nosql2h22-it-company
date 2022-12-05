package com.nosql.document.converter

import com.nosql.document.dto.ExportDocumentDto
import com.nosql.document.entity.DocumentEntity
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class DocumentEntityToExportDocumentDtoConverter: Converter<DocumentEntity, ExportDocumentDto> {

    override fun convert(source: DocumentEntity) = ExportDocumentDto(
        id = source.id!!.toHexString(),
        type = source.type,
        userId = source.userId!!.toHexString(),
        orderDate = source.orderDate,
        completeDate =  source.completeDate,
        status = source.status,
    )
}