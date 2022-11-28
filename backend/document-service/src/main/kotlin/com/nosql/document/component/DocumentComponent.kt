package com.nosql.document.component

import com.nosql.document.entity.DocumentEntity
import org.bson.types.ObjectId

interface DocumentComponent {

    suspend fun save(document: DocumentEntity): DocumentEntity

    suspend fun get(documentId: ObjectId): DocumentEntity

    suspend fun update(document: DocumentEntity)

    suspend fun delete(documentId: ObjectId)
}