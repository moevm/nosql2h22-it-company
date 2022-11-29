package com.nosql.personservice.controller

import com.nosql.personservice.constants.openapi.SECURITY_SCHEME_IDENTIFIER
import com.nosql.personservice.constants.url.PUBLIC_API_V1_PROJECT_URL_PATH
import com.nosql.personservice.service.ProjectService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(PUBLIC_API_V1_PROJECT_URL_PATH)
@SecurityRequirement(name = SECURITY_SCHEME_IDENTIFIER)
class ProjectController(
    private val projectService: ProjectService,
) {

    @GetMapping(
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun getAll(@PageableDefault(page = 0, size = 20) pageable: Pageable) = projectService.getAll(pageable)

    @GetMapping(
        value = [PROJECT_ID_URL_PATH],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun getById(@PathVariable id: String) = projectService.getById(id)

    companion object {
        private const val PROJECT_ID_URL_PATH = "{id}"
    }
}
