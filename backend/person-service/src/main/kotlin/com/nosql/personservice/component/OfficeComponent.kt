package com.nosql.personservice.component

import com.nosql.personservice.entity.OfficeEntity
import org.springframework.data.domain.Pageable

interface OfficeComponent {

    suspend fun getAll(pageable: Pageable): List<OfficeEntity>
}