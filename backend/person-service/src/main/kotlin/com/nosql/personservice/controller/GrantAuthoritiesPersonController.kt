package com.nosql.personservice.controller

import com.nosql.personservice.constants.openapi.SECURITY_SCHEME_IDENTIFIER
import com.nosql.personservice.constants.url.ADMIN_API_V1_PERSON_URL_PATH
import com.nosql.personservice.dto.PersonDto
import com.nosql.personservice.dto.export_import.office.ImportOfficeDto
import com.nosql.personservice.dto.export_import.person.ImportPersonDto
import com.nosql.personservice.dto.export_import.project.ImportProjectDto
import com.nosql.personservice.service.ExportService
import com.nosql.personservice.service.ImportService
import com.nosql.personservice.service.PersonService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(ADMIN_API_V1_PERSON_URL_PATH)
@SecurityRequirement(name = SECURITY_SCHEME_IDENTIFIER)
class GrantAuthoritiesPersonController(
    private val personService: PersonService,
    private val importService: ImportService,
    private val exportService: ExportService,
) {

    @PostMapping(
        path = [SIGN_UP_URL_PATH],
        consumes = [APPLICATION_JSON_VALUE],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun signUp(
        @Valid @RequestBody personDto: PersonDto,
    ) = personService.signUp(personDto)

    @DeleteMapping(
        path = [PERSON_ID_URL_PATH],
        consumes = [APPLICATION_JSON_VALUE],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun delete(
        @PathVariable personId: String,
    ) = personService.delete(personId)

    @PutMapping(
        path = [PERSON_ID_URL_PATH],
        consumes = [APPLICATION_JSON_VALUE],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun update(
        @PathVariable personId: String,
        @Valid @RequestBody personDto: PersonDto,
    ) = personService.update(personId, personDto)

    @PostMapping(
        path = [IMPORT_PEOPLE_URL_PATH],
        consumes = [APPLICATION_JSON_VALUE],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun importPeople(
        @RequestBody people: List<ImportPersonDto>,
    ) = importService.importPeople(people)

    @PostMapping(
        path = [IMPORT_PROJECTS_URL_PATH],
        consumes = [APPLICATION_JSON_VALUE],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun importProjects(
        @RequestBody projects: List<ImportProjectDto>,
    ) = importService.importProjects(projects)

    @PostMapping(
        path = [IMPORT_OFFICES_URL_PATH],
        consumes = [APPLICATION_JSON_VALUE],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun importOffices(
        @RequestBody offices: List<ImportOfficeDto>,
    ) = importService.importOffices(offices)

    @GetMapping(
        path = [EXPORT_PEOPLE_URL_PATH],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun exportPeople(
        @RequestParam(PAGE_REQUEST_PARAM, required = false, defaultValue = "0") page: Int,
    ) = exportService.exportPeople(page)

    @GetMapping(
        path = [EXPORT_PROJECTS_URL_PATH],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun exportProjects(
        @RequestParam(PAGE_REQUEST_PARAM, required = false, defaultValue = "0") page: Int,
    ) = exportService.exportProjects(page)

    @GetMapping(
        path = [EXPORT_OFFICES_URL_PATH],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun exportOffices(
        @RequestParam(PAGE_REQUEST_PARAM, required = false, defaultValue = "0") page: Int,
    ) = exportService.exportOffices(page)

    companion object {

        private const val SIGN_UP_URL_PATH = "sign-up"
        private const val PERSON_ID_URL_PATH = "{personId}"
        private const val IMPORT_URL_PATH = "import"
        const val EXPORT_URL_PATH = "export"
        const val IMPORT_PEOPLE_URL_PATH = "$IMPORT_URL_PATH/people"
        const val IMPORT_OFFICES_URL_PATH = "$IMPORT_URL_PATH/offices"
        const val IMPORT_PROJECTS_URL_PATH = "$IMPORT_URL_PATH/projects"
        const val EXPORT_PEOPLE_URL_PATH = "$EXPORT_URL_PATH/people"
        const val EXPORT_OFFICES_URL_PATH = "$EXPORT_URL_PATH/offices"
        const val EXPORT_PROJECTS_URL_PATH = "$EXPORT_URL_PATH/projects"
        private const val PAGE_REQUEST_PARAM = "page"
    }
}
