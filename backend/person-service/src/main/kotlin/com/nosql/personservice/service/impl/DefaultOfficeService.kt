package com.nosql.personservice.service.impl

import com.nosql.personservice.component.OfficeComponent
import com.nosql.personservice.dto.OfficeDto
import com.nosql.personservice.service.OfficeService
import com.nosql.personservice.util.convert
import org.bson.types.ObjectId
import org.springframework.core.convert.ConversionService
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class DefaultOfficeService(
    private val officeComponent: OfficeComponent,
    private val conversionService: ConversionService,
) : OfficeService {

    override suspend fun getById(id: String) = officeComponent.getById(ObjectId(id))
        .let { conversionService.convert(it, OfficeDto::class) }

    override suspend fun getAll(pageable: Pageable) = officeComponent.getAll(pageable)
        .map { conversionService.convert(it, OfficeDto::class) }
}