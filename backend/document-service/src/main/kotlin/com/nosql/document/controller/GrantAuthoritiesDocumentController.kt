package com.nosql.document.controller

import com.nosql.document.constants.openapi.SECURITY_SCHEME_IDENTIFIER
import com.nosql.document.constants.url.ADMIN_API_V1_DOCUMENT_URL_PATH
import com.nosql.document.dto.ImportDocumentDto
import com.nosql.document.dto.StatusDto
import com.nosql.document.enumerator.DocumentStatus
import com.nosql.document.enumerator.DocumentType
import com.nosql.document.service.DocumentService
import com.nosql.document.service.ExportService
import com.nosql.document.service.ImportService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(ADMIN_API_V1_DOCUMENT_URL_PATH)
@SecurityRequirement(name = SECURITY_SCHEME_IDENTIFIER)
class GrantAuthoritiesDocumentController(
    private val documentService: DocumentService,
    private val importService: ImportService,
    private val exportService: ExportService,
) {

    @GetMapping(
        value = [ALL_URL_PATH],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun getAll(
        @RequestParam(
            TYPE_REQUEST_PARAM,
            defaultValue = "INCOME_STATEMENT, WORK_STATEMENT",
            required = false
        ) types: List<DocumentType>,
        @RequestParam(
            STATUS_REQUEST_PARAM,
            defaultValue = "ORDERED, IN_PROGRESS, DONE, CANCELED",
            required = false,
        ) statuses: List<DocumentStatus>,
        @PageableDefault(page = 0, size = 20) pageable: Pageable,
    ) = documentService.getAll(types, statuses, pageable)

    @PatchMapping(
        value = [ID_URL_PATH],
        consumes = [APPLICATION_JSON_VALUE],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun updateStatus(
        @PathVariable id: String,
        @RequestBody statusDto: StatusDto,
    ) = documentService.update(id, statusDto)

    @PostMapping(
        path = [IMPORT_DOCUMENTS_URL_PATH],
        consumes = [APPLICATION_JSON_VALUE],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun importDocuments(
        @RequestBody documents: List<ImportDocumentDto>,
    ) = importService.importDocuments(documents)

    @GetMapping(
        path = [EXPORT_DOCUMENTS_URL_PATH],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun exportDocuments(
        @RequestParam(PAGE_REQUEST_PARAM, required = false, defaultValue = "0") page: Int,
    ) = exportService.exportDocuments(page)

    companion object {
        private const val TYPE_REQUEST_PARAM = "type"
        private const val STATUS_REQUEST_PARAM = "status"
        private const val PAGE_REQUEST_PARAM = "page"
        private const val ALL_URL_PATH = "all"
        private const val ID_URL_PATH = "{id}"
        private const val IMPORT_URL_PATH = "import"
        const val EXPORT_URL_PATH = "export"
        private const val IMPORT_DOCUMENTS_URL_PATH = "$IMPORT_URL_PATH/documents"
        const val EXPORT_DOCUMENTS_URL_PATH = "$EXPORT_URL_PATH/documents"
    }
}