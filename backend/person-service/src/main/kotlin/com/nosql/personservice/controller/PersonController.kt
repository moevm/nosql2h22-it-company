package com.nosql.personservice.controller

import com.nosql.personservice.constants.url.PUBLIC_API_V1_PERSON_URL_PATH
import com.nosql.personservice.dto.PersonDto
import com.nosql.personservice.service.PersonService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(PUBLIC_API_V1_PERSON_URL_PATH)
class PersonController(
    private val personService: PersonService,
) {

    @PostMapping(
        path = [SIGN_UP_URL_PATH],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE],
    )
    suspend fun signUp(
        @Valid @RequestBody personDto: PersonDto,
    ) = personService.signUp(personDto)

    @DeleteMapping(
        path = [DELETE_URL_PATH],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE],
    )
    suspend fun delete(
        @PathVariable personId: String,
    ) = personService.delete(personId)

    companion object {
        private const val SIGN_UP_URL_PATH = "sign-up"
        private const val DELETE_URL_PATH = "{personId}"
    }
}