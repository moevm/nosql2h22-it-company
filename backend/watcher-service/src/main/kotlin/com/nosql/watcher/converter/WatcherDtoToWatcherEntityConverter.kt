package com.nosql.watcher.converter

import com.nosql.watcher.dto.WatcherDto
import com.nosql.watcher.entity.WatcherEntity
import org.bson.types.ObjectId
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class WatcherDtoToWatcherEntityConverter : Converter<WatcherDto, WatcherEntity> {
    override fun convert(source: WatcherDto) = WatcherEntity(
        id = ObjectId(source.id),
        date = source.date,
        projectId = ObjectId(source.projectId),
        minutesAmount = source.minutesAmount,
        comment = source.comment,
    )
}