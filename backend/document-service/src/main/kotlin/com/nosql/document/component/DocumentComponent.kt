package com.nosql.document.component

import com.nosql.document.entity.DocumentEntity
import com.nosql.document.enumerator.DocumentStatus
import com.nosql.document.enumerator.DocumentType
import org.bson.types.ObjectId
import org.springframework.data.domain.Pageable

interface DocumentComponent {

    suspend fun save(document: DocumentEntity): DocumentEntity

    suspend fun get(documentId: ObjectId): DocumentEntity

    suspend fun getAll(
        types: List<DocumentType>,
        statuses: List<DocumentStatus>,
        pageable: Pageable,
    ): List<DocumentEntity>

    suspend fun getAllByUserId(userId: ObjectId, pageable: Pageable): List<DocumentEntity>

    suspend fun update(id: ObjectId, document: DocumentEntity): DocumentEntity

    suspend fun delete(documentId: ObjectId)
}