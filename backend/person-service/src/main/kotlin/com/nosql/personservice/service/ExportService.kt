package com.nosql.personservice.service

import org.springframework.core.io.ByteArrayResource

interface ExportService {

    suspend fun exportPeople(page: Int): ByteArrayResource

    suspend fun exportProjects(page: Int): ByteArrayResource

    suspend fun exportOffices(page: Int): ByteArrayResource
}