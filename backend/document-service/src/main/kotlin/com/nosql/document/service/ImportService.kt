package com.nosql.document.service

import com.nosql.document.dto.ImportDocumentDto
import com.nosql.document.dto.ImportResponseDto


interface ImportService {

    suspend fun importDocuments(documents: List<ImportDocumentDto>): ImportResponseDto


}