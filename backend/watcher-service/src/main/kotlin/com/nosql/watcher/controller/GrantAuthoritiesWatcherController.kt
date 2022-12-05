package com.nosql.watcher.controller

import com.nosql.watcher.constants.jackson.DATE_PATTERN
import com.nosql.watcher.constants.openapi.SECURITY_SCHEME_IDENTIFIER
import com.nosql.watcher.constants.url.ADMIN_API_V1_WATCHER_URL_PATH
import com.nosql.watcher.dto.ImportWatcherDto
import com.nosql.watcher.service.ExportService
import com.nosql.watcher.service.ImportService
import com.nosql.watcher.service.WatcherService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.MediaType
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.Date

@RestController
@RequestMapping(ADMIN_API_V1_WATCHER_URL_PATH)
@SecurityRequirement(name = SECURITY_SCHEME_IDENTIFIER)
class GrantAuthoritiesWatcherController(
    private val watcherService: WatcherService,
    private val importService: ImportService,
    private val exportService: ExportService,
) {

    @GetMapping(
        value = [SUBORDINATE_URL_PATH],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun getAllByUserIdsBetweenDates(
        @RequestParam(IDS_QUERY_PARAM) ids: List<String>,
        @RequestParam(FROM_QUERY_PARAM) @DateTimeFormat(pattern = DATE_PATTERN) from: Date,
        @RequestParam(TO_QUERY_PARAM) @DateTimeFormat(pattern = DATE_PATTERN) to: Date,
        @PageableDefault(page = 0, size = 20) pageable: Pageable,
    ) = watcherService.getAllByUserIdsAndDate(ids, from, to, pageable)

    @GetMapping(
        value = [ALL_URL_PATH],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun getAll(
        @PageableDefault(page = 0, size = 20) pageable: Pageable,
    ) = watcherService.getAll(pageable)

    @PostMapping(
        path = [IMPORT_WATCHERS_URL_PATH],
        consumes = [APPLICATION_JSON_VALUE],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun importWatchers(
        @RequestBody watchers: List<ImportWatcherDto>,
    ) = importService.importDocuments(watchers)

    @GetMapping(
        path = [EXPORT_WATCHERS_URL_PATH],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun exportWatchers(
        @RequestParam(PAGE_REQUEST_PARAM, required = false, defaultValue = "0") page: Int,
    ) = exportService.exportWatchers(page)


    companion object {
        private const val ALL_URL_PATH = "all"
        private const val SUBORDINATE_URL_PATH = "subordinate"
        private const val PAGE_REQUEST_PARAM = "page"
        private const val IDS_QUERY_PARAM = "ids"
        private const val FROM_QUERY_PARAM = "from"
        private const val TO_QUERY_PARAM = "to"
        private const val IMPORT_URL_PATH = "import"
        const val EXPORT_URL_PATH = "export"
        private const val IMPORT_WATCHERS_URL_PATH = "$IMPORT_URL_PATH/watchers"
        private const val EXPORT_WATCHERS_URL_PATH = "$EXPORT_URL_PATH/watchers"
    }
}