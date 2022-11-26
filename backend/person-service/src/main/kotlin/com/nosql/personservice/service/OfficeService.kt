package com.nosql.personservice.service

import com.nosql.personservice.dto.OfficeDto
import org.springframework.data.domain.Pageable

interface OfficeService {

    suspend fun getAll(pageable: Pageable): List<OfficeDto>
}