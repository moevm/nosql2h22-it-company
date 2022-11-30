package com.nosql.watcher.controller

import com.nosql.watcher.common.auth.UserAuth
import com.nosql.watcher.common.auth.UserAuthInfo
import com.nosql.watcher.constants.jackson.DATE_PATTERN
import com.nosql.watcher.constants.openapi.SECURITY_SCHEME_IDENTIFIER
import com.nosql.watcher.constants.url.PUBLIC_API_V1_WATCHER_URL_PATH
import com.nosql.watcher.dto.WatcherDto
import com.nosql.watcher.service.WatcherService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.MediaType.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.Date
import javax.validation.Valid

@RestController
@RequestMapping(PUBLIC_API_V1_WATCHER_URL_PATH)
@SecurityRequirement(name = SECURITY_SCHEME_IDENTIFIER)
class WatcherController(
    private val watcherService: WatcherService,
) {

    @GetMapping(
        value = [ALL_OWN_BETWEEN_DATES_URL_PATH],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun getAllByUserIdAndDate(
        @UserAuth userAuthInfo: UserAuthInfo,
        @RequestParam(FROM_QUERY_PARAM) @DateTimeFormat(pattern = DATE_PATTERN) from: Date,
        @RequestParam(TO_QUERY_PARAM) @DateTimeFormat(pattern = DATE_PATTERN) to: Date,
        @PageableDefault(page = 0, size = 20) pageable: Pageable,
    ) = watcherService.getAllByUserIdAndDate(userAuthInfo.userId, from, to, pageable)

    @GetMapping(
        value = [SICK_URL_PATH],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun getAllByUserIdAndDateAndSick(
        @UserAuth userAuthInfo: UserAuthInfo,
        @RequestParam(FROM_QUERY_PARAM) @DateTimeFormat(pattern = DATE_PATTERN) from: Date,
        @RequestParam(TO_QUERY_PARAM) @DateTimeFormat(pattern = DATE_PATTERN) to: Date,
        @PageableDefault(page = 0, size = 20) pageable: Pageable,
    ) = watcherService.getAllByUserIdAndSick(userAuthInfo.userId, from, to, pageable)

    @GetMapping(
        value = [VACATION_URL_PATH],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun getAllByUserIdAndDateAndVacation(
        @UserAuth userAuthInfo: UserAuthInfo,
        @RequestParam(FROM_QUERY_PARAM) @DateTimeFormat(pattern = DATE_PATTERN) from: Date,
        @RequestParam(TO_QUERY_PARAM) @DateTimeFormat(pattern = DATE_PATTERN) to: Date,
        @PageableDefault(page = 0, size = 20) pageable: Pageable,
    ) = watcherService.getAllByUserIdAndVacation(userAuthInfo.userId, from, to, pageable)

    @GetMapping(
        value = [ALL_URL_PATH],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun getAll(
        @PageableDefault(page = 0, size = 20) pageable: Pageable,
    ) = watcherService.getAll(pageable)

    @GetMapping(
        value = [ID_URL_PATH],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun get(
        @PathVariable id: String,
    ) = watcherService.get(id)

    @PostMapping(
        consumes = [APPLICATION_JSON_VALUE],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun create(
        @UserAuth userAuthInfo: UserAuthInfo,
        @Valid @RequestBody watcherDto: WatcherDto,
    ) = watcherService.save(userAuthInfo.userId, watcherDto)

    companion object {
        private const val ALL_URL_PATH = "all"
        private const val ALL_OWN_BETWEEN_DATES_URL_PATH = "all-own-between-dates"
        private const val SICK_URL_PATH = "sick"
        private const val VACATION_URL_PATH = "vacation"
        private const val ID_URL_PATH = "{id}"
        private const val FROM_QUERY_PARAM = "from"
        private const val TO_QUERY_PARAM = "to"
    }
}