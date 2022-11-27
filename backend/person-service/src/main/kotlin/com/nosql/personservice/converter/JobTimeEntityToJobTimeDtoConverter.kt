package com.nosql.personservice.converter

import com.nosql.personservice.dto.JobTimeDto
import com.nosql.personservice.entity.JobTimeEntity
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class JobTimeEntityToJobTimeDtoConverter : Converter<JobTimeEntity, JobTimeDto> {

    override fun convert(from: JobTimeEntity): JobTimeDto {
        return JobTimeDto(
            start = from.start,
            end = from.end,
        )
    }
}