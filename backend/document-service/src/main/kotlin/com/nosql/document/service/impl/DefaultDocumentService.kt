package com.nosql.document.service.impl

import com.nosql.document.component.DocumentComponent
import com.nosql.document.dto.DefaultApiResponseDto
import com.nosql.document.dto.DocumentDto
import com.nosql.document.entity.DocumentEntity
import com.nosql.document.service.DocumentService
import com.nosql.document.util.convert
import org.bson.types.ObjectId
import org.springframework.core.convert.ConversionService
import org.springframework.stereotype.Service

@Service
class DefaultDocumentService(
    private val documentComponent: DocumentComponent,
    private val conversionService: ConversionService,
): DocumentService {

    override suspend fun save(userId: String, documentDto: DocumentDto): DocumentDto {
        val document = makeDocumentEntity(userId, documentDto)
        val savedDocument = documentComponent.save(document)
        return conversionService.convert(savedDocument, DocumentDto::class)
    }

    override suspend fun get(documentId: String): DocumentDto {
        TODO("Not yet implemented")
    }

    override suspend fun update(documentDto: DocumentDto) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(documentId: String): DefaultApiResponseDto {
        TODO("Not yet implemented")
    }

    private fun makeDocumentEntity(userId: String, documentDto: DocumentDto) =
        conversionService.convert(documentDto, DocumentEntity::class)
            .apply { this.userId = ObjectId(userId) }

}