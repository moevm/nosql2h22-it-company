package com.nosql.personservice.component

import com.nosql.personservice.entity.OfficeEntity
import org.bson.types.ObjectId
import org.springframework.data.domain.Pageable

interface OfficeComponent {

    suspend fun getById(id: ObjectId): OfficeEntity

    suspend fun getAll(pageable: Pageable): List<OfficeEntity>
}