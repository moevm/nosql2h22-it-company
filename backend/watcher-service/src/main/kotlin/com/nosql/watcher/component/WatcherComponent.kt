package com.nosql.watcher.component

import com.nosql.watcher.entity.WatcherEntity
import org.bson.types.ObjectId
import org.springframework.data.domain.Pageable
import java.util.Date

interface WatcherComponent {

    suspend fun save(watcher: WatcherEntity): WatcherEntity

    suspend fun get(watcherId: ObjectId): WatcherEntity

    suspend fun getAllByUserIdAndDate(userId: ObjectId, date: Date, pageable: Pageable): List<WatcherEntity>

    suspend fun update(watcher: WatcherEntity)

    suspend fun delete(watcherId: ObjectId)
}