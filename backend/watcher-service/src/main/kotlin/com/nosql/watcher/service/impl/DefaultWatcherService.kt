package com.nosql.watcher.service.impl

import com.nosql.watcher.component.ProjectComponent
import com.nosql.watcher.component.WatcherComponent
import com.nosql.watcher.dto.DefaultApiResponseDto
import com.nosql.watcher.dto.WatcherDto
import com.nosql.watcher.entity.WatcherEntity
import com.nosql.watcher.service.WatcherService
import com.nosql.watcher.util.convert
import org.bson.types.ObjectId
import org.springframework.core.convert.ConversionService
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.Date

@Service
class DefaultWatcherService(
    private val watcherComponent: WatcherComponent,
    private val projectComponent: ProjectComponent,
    private val conversionService: ConversionService,
): WatcherService {
    override suspend fun save(userId: String, watcherDto: WatcherDto): WatcherDto {
        val watcher = makeWatcherEntity(userId, watcherDto)
        val savedWatcher = watcherComponent.save(watcher)
        return conversionService.convert(savedWatcher, WatcherDto::class)
    }

    override suspend fun get(watcherId: String) =  watcherComponent.get(ObjectId(watcherId))
        .let { conversionService.convert(it, WatcherDto::class) }

    override suspend fun getAll(pageable: Pageable) = watcherComponent.getAll(pageable)
        .map { conversionService.convert(it, WatcherDto::class) }

    override suspend fun getAllByUserIdAndDate(userId: String, from: Date, to: Date, pageable: Pageable) =
        watcherComponent.getAllByUserIdAndDate(ObjectId(userId), from, to, pageable)
            .map { conversionService.convert(it, WatcherDto::class) }

    override suspend fun getAllByUserIdAndSick(
        userId: String,
        from: Date,
        to: Date,
        pageable: Pageable,
    ): List<WatcherDto> {
        val projectId = projectComponent.getIdByName(SICK_PROJECT_NAME)
        return watcherComponent.getAllByUserIdAndDateAndProjectId(ObjectId(userId), projectId, from, to, pageable)
            .map { conversionService.convert(it, WatcherDto::class) }
    }

    override suspend fun getAllByUserIdAndVacation(
        userId: String,
        from: Date,
        to: Date,
        pageable: Pageable,
    ): List<WatcherDto> {
        val projectId = projectComponent.getIdByName(VACATION_PROJECT_NAME)
        return watcherComponent.getAllByUserIdAndDateAndProjectId(ObjectId(userId), projectId, from, to, pageable)
            .map { conversionService.convert(it, WatcherDto::class) }
    }

    override suspend fun update(watcherDto: WatcherDto) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(watcherId: String): DefaultApiResponseDto {
        TODO("Not yet implemented")
    }

    private fun makeWatcherEntity(userId: String, watcherDto: WatcherDto) =
        conversionService.convert(watcherDto, WatcherEntity::class)
            .apply { this.userId = ObjectId(userId) }


    companion object {
        private const val SICK_PROJECT_NAME = "Больничный"
        private const val VACATION_PROJECT_NAME = "Отпуск"
    }
}