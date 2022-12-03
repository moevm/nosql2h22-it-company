package com.nosql.document.service.impl

import com.nosql.document.component.DocumentComponent
import com.nosql.document.dto.DefaultApiResponseDto
import com.nosql.document.dto.DocumentDto
import com.nosql.document.dto.PersonDocumentResponseDto
import com.nosql.document.dto.StatusDto
import com.nosql.document.entity.DocumentEntity
import com.nosql.document.enumerator.DocumentStatus
import com.nosql.document.enumerator.DocumentType
import com.nosql.document.mapper.merge
import com.nosql.document.service.DocumentService
import com.nosql.document.util.convert
import org.bson.types.ObjectId
import org.springframework.core.convert.ConversionService
import org.springframework.data.domain.Pageable
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

    override suspend fun get(documentId: String) =  documentComponent.get(ObjectId(documentId))
        .let { conversionService.convert(it, DocumentDto::class) }

    override suspend fun getAll(
        types: List<DocumentType>,
        statuses: List<DocumentStatus>,
        pageable: Pageable,
    ) = documentComponent.getAll(types, statuses, pageable)
        .map { conversionService.convert(it, PersonDocumentResponseDto::class) }

    override suspend fun getAllByUserId(userId: String, pageable: Pageable) =
        documentComponent.getAllByUserId(ObjectId(userId), pageable)
            .map { conversionService.convert(it, DocumentDto::class) }

    override suspend fun update(id: String, statusDto: StatusDto) =
        documentComponent.get(ObjectId(id))
            .apply { merge(statusDto) }
            .let { documentComponent.update(it) }
            .let { conversionService.convert(it, PersonDocumentResponseDto::class) }

    override suspend fun delete(documentId: String): DefaultApiResponseDto {
        TODO("Not yet implemented")
    }

    private fun makeDocumentEntity(userId: String, documentDto: DocumentDto) =
        conversionService.convert(documentDto, DocumentEntity::class)
            .apply { this.userId = ObjectId(userId) }

}