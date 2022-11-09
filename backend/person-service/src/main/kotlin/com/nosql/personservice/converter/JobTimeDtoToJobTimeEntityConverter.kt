package com.nosql.personservice.converter

import com.nosql.personservice.dto.JobTimeDto
import com.nosql.personservice.entity.JobTimeEntity
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class JobTimeDtoToJobTimeEntityConverter : Converter<JobTimeDto, JobTimeEntity> {

    override fun convert(from: JobTimeDto): JobTimeEntity {
        return JobTimeEntity(
            start = from.start,
            end = from.end,
        )
    }
}