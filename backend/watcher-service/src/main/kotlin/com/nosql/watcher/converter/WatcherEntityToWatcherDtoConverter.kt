package com.nosql.watcher.converter

import com.nosql.watcher.dto.WatcherDto
import com.nosql.watcher.entity.WatcherEntity
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class WatcherEntityToWatcherDtoConverter : Converter<WatcherEntity, WatcherDto> {

    override fun convert(source: WatcherEntity) = WatcherDto(
        id = source.id.toHexString(),
        date = source.date,
        projectId = source.projectId.toHexString(),
        minutesAmount = source.minutesAmount,
        comment = source.comment,
    )
}