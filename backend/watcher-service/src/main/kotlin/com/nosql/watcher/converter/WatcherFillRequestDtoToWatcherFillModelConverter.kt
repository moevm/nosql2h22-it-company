package com.nosql.watcher.converter

import com.nosql.watcher.dto.WatcherFillRequestDto
import com.nosql.watcher.entity.WatcherFillModel
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class WatcherFillRequestDtoToWatcherFillModelConverter : Converter<WatcherFillRequestDto, WatcherFillModel> {

    override fun convert(source: WatcherFillRequestDto) = WatcherFillModel(
        minutesAmount = source.minutesAmount,
        comment = source.comment,
    )
}