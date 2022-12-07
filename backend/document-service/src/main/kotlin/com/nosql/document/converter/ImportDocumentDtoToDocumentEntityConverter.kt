package com.nosql.document.converter

import com.nosql.document.dto.ImportDocumentDto
import com.nosql.document.entity.DocumentEntity
import com.nosql.document.enumerator.DocumentStatus
import com.nosql.document.enumerator.DocumentType
import org.bson.types.ObjectId
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat

@Component
class ImportDocumentDtoToDocumentEntityConverter: Converter<ImportDocumentDto, DocumentEntity> {

    override fun convert(source: ImportDocumentDto) = DocumentEntity(
        id = ObjectId(source.id!!),
        type = DocumentType.get(source.type!!),
        userId = ObjectId(source.userId),
        orderDate = dateFormatter.parse(source.orderDate!!),
        completeDate = source.completeDate?.let { dateFormatter.parse(source.completeDate!!) },
        status = DocumentStatus.get(source.status!!)
    )

    companion object {
        private val dateFormatter = SimpleDateFormat("yyyy-MM-dd")
    }
}