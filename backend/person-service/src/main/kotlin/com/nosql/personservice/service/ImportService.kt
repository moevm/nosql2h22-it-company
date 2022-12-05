package com.nosql.personservice.service

import com.nosql.personservice.dto.export_import.ImportResponseDto
import com.nosql.personservice.dto.export_import.office.ImportOfficeDto
import com.nosql.personservice.dto.export_import.person.ImportPersonDto
import com.nosql.personservice.dto.export_import.project.ImportProjectDto


interface ImportService {

    suspend fun importPeople(people: List<ImportPersonDto>): ImportResponseDto

    suspend fun importOffices(offices: List<ImportOfficeDto>): ImportResponseDto

    suspend fun importProjects(projects: List<ImportProjectDto>): ImportResponseDto

}