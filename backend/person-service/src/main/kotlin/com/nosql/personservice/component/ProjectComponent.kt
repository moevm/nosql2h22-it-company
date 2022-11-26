package com.nosql.personservice.component

import com.nosql.personservice.entity.ProjectEntity
import org.springframework.data.domain.Pageable

interface ProjectComponent {

    suspend fun getAll(pageable: Pageable): List<ProjectEntity>
}