package com.nosql.watcher.component

import com.nosql.watcher.entity.WatcherEntity
import org.bson.types.ObjectId
import org.springframework.data.domain.Pageable
import java.util.Date

interface WatcherComponent {

    suspend fun save(watcher: WatcherEntity): WatcherEntity

    suspend fun get(watcherId: ObjectId): WatcherEntity

    suspend fun getAll(pageable: Pageable): List<WatcherEntity>

    suspend fun getAllByUserIdAndDate(userId: ObjectId, from: Date, to: Date, pageable: Pageable): List<WatcherEntity>

    suspend fun getAllByUserIdAndDateAndProjectId(
        userId: ObjectId,
        projectId: ObjectId,
        from: Date, to:
        Date, pageable:
        Pageable
    ): List<WatcherEntity>

    suspend fun update(watcher: WatcherEntity)

    suspend fun delete(watcherId: ObjectId)
}