package com.nosql.document.common.filter

import com.nosql.document.constants.url.ADMIN_API_V1_DOCUMENT_URL_PATH
import com.nosql.document.controller.GrantAuthoritiesDocumentController.Companion.EXPORT_DOCUMENTS_URL_PATH
import com.nosql.document.controller.GrantAuthoritiesDocumentController.Companion.EXPORT_URL_PATH
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
                .add("Content-Disposition", "attachment; filename=documents.json" )
        }
        return chain.filter(exchange)
    }

    companion object {
        private const val IMPORT_URL_PATH_REGEX = ".*$ADMIN_API_V1_DOCUMENT_URL_PATH/$EXPORT_URL_PATH.*"
    }
}