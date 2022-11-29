package com.nosql.document.component

import com.nosql.document.entity.DocumentEntity
import org.bson.types.ObjectId
import org.springframework.data.domain.Pageable

interface DocumentComponent {

    suspend fun save(document: DocumentEntity): DocumentEntity

    suspend fun get(documentId: ObjectId): DocumentEntity

    suspend fun getAll(pageable: Pageable): List<DocumentEntity>

    suspend fun getAllByUserId(userId: ObjectId, pageable: Pageable): List<DocumentEntity>

    suspend fun update(document: DocumentEntity)

    suspend fun delete(documentId: ObjectId)
}