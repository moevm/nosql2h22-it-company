package com.nosql.personservice.component

import com.nosql.personservice.entity.ProjectEntity
import org.bson.types.ObjectId
import org.springframework.data.domain.Pageable

interface ProjectComponent {

    suspend fun saveAll(projects: List<ProjectEntity>): List<ProjectEntity>

    suspend fun getById(id: ObjectId): ProjectEntity

    suspend fun getAll(pageable: Pageable): List<ProjectEntity>

    suspend fun getAllByIds(ids: List<ObjectId>): List<ProjectEntity>
}