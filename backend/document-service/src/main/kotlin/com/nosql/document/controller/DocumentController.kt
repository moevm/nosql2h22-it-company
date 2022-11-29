package com.nosql.document.controller

import com.nosql.document.common.auth.UserAuth
import com.nosql.document.common.auth.UserAuthInfo
import com.nosql.document.constants.openapi.SECURITY_SCHEME_IDENTIFIER
import com.nosql.document.constants.url.PUBLIC_API_V1_DOCUMENT_URL_PATH
import com.nosql.document.dto.DocumentDto
import com.nosql.document.service.DocumentService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(PUBLIC_API_V1_DOCUMENT_URL_PATH)
@SecurityRequirement(name = SECURITY_SCHEME_IDENTIFIER)
class DocumentController(
    private val documentService: DocumentService,
) {

    @GetMapping(
        value = [ALL_OWN_URL_PATH],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun getAllByUserId(
        @UserAuth userAuthInfo: UserAuthInfo,
        @PageableDefault(page = 0, size = 20) pageable: Pageable,
    ) = documentService.getAllByUserId(userAuthInfo.userId, pageable)

    @GetMapping(
        value = [ALL_URL_PATH],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun getAll(
        @PageableDefault(page = 0, size = 20) pageable: Pageable,
    ) = documentService.getAll(pageable)

    @GetMapping(
        value = [ID_URL_PATH],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun get(
        @PathVariable id: String,
    ) = documentService.get(id)

    @PostMapping(
        consumes = [APPLICATION_JSON_VALUE],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun create(
        @UserAuth userAuthInfo: UserAuthInfo,
        @Valid @RequestBody documentDto: DocumentDto,
    ) = documentService.save(userAuthInfo.userId, documentDto)

    companion object {
        private const val ALL_URL_PATH = "all"
        private const val ALL_OWN_URL_PATH = "all-own"
        private const val ID_URL_PATH = "{id}"
    }
}