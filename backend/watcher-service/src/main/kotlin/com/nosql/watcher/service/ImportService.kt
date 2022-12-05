package com.nosql.watcher.service

import com.nosql.watcher.dto.ImportResponseDto
import com.nosql.watcher.dto.ImportWatcherDto


interface ImportService {

    suspend fun importDocuments(documents: List<ImportWatcherDto>): ImportResponseDto

}