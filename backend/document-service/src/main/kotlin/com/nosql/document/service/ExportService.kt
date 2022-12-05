package com.nosql.document.service

import org.springframework.core.io.ByteArrayResource

interface ExportService {

    suspend fun exportDocuments(page: Int): ByteArrayResource
}