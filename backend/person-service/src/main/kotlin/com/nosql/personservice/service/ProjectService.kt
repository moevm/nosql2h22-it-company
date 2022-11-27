package com.nosql.personservice.service

import com.nosql.personservice.dto.ProjectDto
import org.springframework.data.domain.Pageable

interface ProjectService {

    suspend fun getAll(pageable: Pageable): List<ProjectDto>
}