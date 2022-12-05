package com.nosql.personservice.common.filter

import com.nosql.personservice.constants.url.ADMIN_API_V1_PERSON_URL_PATH
import com.nosql.personservice.controller.GrantAuthoritiesPersonController.Companion.EXPORT_URL_PATH
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
                .add("Content-Disposition", "attachment; filename=person.json")
        }
        return chain.filter(exchange)
    }

    companion object {
        private const val IMPORT_URL_PATH_REGEX = ".*$ADMIN_API_V1_PERSON_URL_PATH/$EXPORT_URL_PATH.*"
    }
}