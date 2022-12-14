package com.nosql.watcher.service

import com.nosql.watcher.dto.DefaultApiResponseDto
import com.nosql.watcher.dto.WatcherDto
import com.nosql.watcher.dto.WatcherFillRequestDto
import org.springframework.data.domain.Pageable
import java.util.Date

interface WatcherService {

    suspend fun save(userId: String, watcherDto: WatcherDto): WatcherDto

    suspend fun get(watcherId: String): WatcherDto

    suspend fun getAll(pageable: Pageable): List<WatcherDto>

    suspend fun getAllByUserIdsAndDate(
        userIds: List<String>,
        from: Date,
        to: Date,
        pageable: Pageable,
    ): List<WatcherDto>

    suspend fun getAllByUserIdAndDate(userId: String, from: Date, to: Date, pageable: Pageable): List<WatcherDto>

    suspend fun getAllByUserIdAndSick(userId: String, from: Date, to: Date, pageable: Pageable): List<WatcherDto>

    suspend fun getAllByUserIdAndVacation(userId: String, from: Date, to: Date, pageable: Pageable): List<WatcherDto>

    suspend fun update(id: String, watcherFillRequestDto: WatcherFillRequestDto): WatcherDto

    suspend fun delete(watcherId: String): DefaultApiResponseDto
}