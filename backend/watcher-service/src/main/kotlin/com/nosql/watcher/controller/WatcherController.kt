package com.nosql.watcher.controller

import com.nosql.watcher.common.auth.UserAuth
import com.nosql.watcher.common.auth.UserAuthInfo
import com.nosql.watcher.constants.url.PUBLIC_API_V1_WATCHER_URL_PATH
import com.nosql.watcher.dto.WatcherDto
import com.nosql.watcher.service.WatcherService
import org.springframework.http.MediaType.*
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(PUBLIC_API_V1_WATCHER_URL_PATH)
class WatcherController(
    private val watcherService: WatcherService,
) {

    @PostMapping(
        consumes = [APPLICATION_JSON_VALUE],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun create(
        @UserAuth userAuthInfo: UserAuthInfo,
        @Valid @RequestBody watcherDto: WatcherDto,
    ) = watcherService.save(userAuthInfo.userId, watcherDto)
}