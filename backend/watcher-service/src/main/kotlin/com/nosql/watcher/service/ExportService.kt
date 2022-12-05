package com.nosql.watcher.service

import org.springframework.core.io.ByteArrayResource

interface ExportService {

    suspend fun exportWatchers(page: Int): ByteArrayResource
}