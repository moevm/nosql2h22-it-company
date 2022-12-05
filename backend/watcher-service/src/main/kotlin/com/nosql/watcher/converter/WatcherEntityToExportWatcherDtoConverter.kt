package com.nosql.watcher.converter

import com.nosql.watcher.dto.ExportWatcherDto
import com.nosql.watcher.entity.WatcherEntity
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class WatcherEntityToExportWatcherDtoConverter: Converter<WatcherEntity, ExportWatcherDto> {

    override fun convert(source: WatcherEntity) = ExportWatcherDto(
        id = source.id!!.toHexString(),
        userId = source.userId!!.toHexString(),
        date = source.date,
        projectId = source.projectId.toHexString(),
        minutesAmount = source.minutesAmount,
        comment = source.comment,
    )
}