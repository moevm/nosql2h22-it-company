package com.nosql.document.service

import com.nosql.document.dto.DefaultApiResponseDto
import com.nosql.document.dto.DocumentDto
import com.nosql.document.enumerator.DocumentStatus
import com.nosql.document.enumerator.DocumentType
import org.springframework.data.domain.Pageable

interface DocumentService {

    suspend fun save(userId: String, documentDto: DocumentDto): DocumentDto

    suspend fun get(documentId: String): DocumentDto

    suspend fun getAll(
        types: List<DocumentType>,
        statuses: List<DocumentStatus>,
        pageable: Pageable,
    ): List<DocumentDto>

    suspend fun getAllByUserId(userId: String, pageable: Pageable): List<DocumentDto>

    suspend fun update(documentDto: DocumentDto)

    suspend fun delete(documentId: String): DefaultApiResponseDto
}