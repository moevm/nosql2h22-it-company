package com.nosql.document.controller

import com.nosql.document.common.auth.UserAuth
import com.nosql.document.common.auth.UserAuthInfo
import com.nosql.document.constants.openapi.SECURITY_SCHEME_IDENTIFIER
import com.nosql.document.constants.url.PUBLIC_API_V1_DOCUMENT_URL_PATH
import com.nosql.document.dto.DocumentDto
import com.nosql.document.service.DocumentService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
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

    @PostMapping(
        consumes = [APPLICATION_JSON_VALUE],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun create(
        @UserAuth userAuthInfo: UserAuthInfo,
        @Valid @RequestBody documentDto: DocumentDto,
    ) = documentService.save(userAuthInfo.userId, documentDto)
}