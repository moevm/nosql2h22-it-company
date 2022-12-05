package com.nosql.watcher.converter

import com.nosql.watcher.dto.ImportWatcherDto
import com.nosql.watcher.entity.WatcherEntity
import org.bson.types.ObjectId
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat

@Component
class ImportWatcherDtoToWatcherEntityConverter: Converter<ImportWatcherDto, WatcherEntity> {

    override fun convert(source: ImportWatcherDto) = WatcherEntity(
        id = ObjectId(source.id!!),
        userId = ObjectId(source.userId!!),
        date = dateFormatter.parse(source.date!!),
        projectId = ObjectId(source.projectId!!),
        minutesAmount = source.minutesAmount!!.toLong(),
        comment = source.comment!!
    )

    companion object {
        private val dateFormatter = SimpleDateFormat("yyyy-MM-dd")
    }
}