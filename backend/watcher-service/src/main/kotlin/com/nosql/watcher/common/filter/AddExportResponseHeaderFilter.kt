package com.nosql.watcher.common.filter

import com.nosql.watcher.constants.url.ADMIN_API_V1_WATCHER_URL_PATH
import com.nosql.watcher.controller.GrantAuthoritiesWatcherController.Companion.EXPORT_URL_PATH
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono
import java.util.regex.Pattern


@Component
class AddExportResponseHeaderFilter : WebFilter {
    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        if (Pattern.matches(IMPORT_URL_PATH_REGEX, exchange.request.path.toString())) {
            exchange.response
                .headers
                .add("Content-Disposition", "attachment; filename=watchers.json" )
        }
        return chain.filter(exchange)
    }

    companion object {
        private const val IMPORT_URL_PATH_REGEX = ".*$ADMIN_API_V1_WATCHER_URL_PATH/$EXPORT_URL_PATH.*"
    }
}