package com.nosql.personservice.controller

import com.nosql.personservice.constants.openapi.SECURITY_SCHEME_IDENTIFIER
import com.nosql.personservice.constants.url.ADMIN_API_V1_PERSON_URL_PATH
import com.nosql.personservice.dto.PersonDto
import com.nosql.personservice.service.PersonService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(ADMIN_API_V1_PERSON_URL_PATH)
@SecurityRequirement(name = SECURITY_SCHEME_IDENTIFIER)
class GrantAuthoritiesPersonController(
    private val personService: PersonService,
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

    companion object {

        private const val SIGN_UP_URL_PATH = "sign-up"
        private const val PERSON_ID_URL_PATH = "{personId}"
    }
}
