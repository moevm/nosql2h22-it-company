package com.nosql.personservice.controller

import com.nosql.personservice.constants.url.PUBLIC_API_V1_PERSON_URL_PATH
import com.nosql.personservice.dto.ContactsDto
import com.nosql.personservice.dto.PersonDto
import com.nosql.personservice.service.PersonService
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
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
@RequestMapping(PUBLIC_API_V1_PERSON_URL_PATH)
class PersonController(
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

    @GetMapping(
        path = [PERSON_ID_URL_PATH],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun get(
        @PathVariable personId: String,
    ) = personService.get(personId)

    @GetMapping(
        path = [ALL_URL_PATH],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun getAll(@PageableDefault(page = 0, size = 20) pageable: Pageable) = personService.getAll(pageable)

    @GetMapping(
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun getAllByName(
        @RequestParam(NAME_QUERY_PARAM) name: String,
        @PageableDefault(page = 0, size = 20) pageable: Pageable,
    ) = personService.getAllByName(name, pageable)

    @GetMapping(
        path = [EXTENDED_URL_PATH],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun extendedGet(
        @RequestParam(NAME_QUERY_PARAM, defaultValue = ".+", required = false) name: String,
        @RequestParam(SURNAME_QUERY_PARAM, defaultValue = ".+", required = false) surname: String,
        @RequestParam(PATRONYMIC_QUERY_PARAM, defaultValue = ".+", required = false) patronymic: String,
        @RequestParam(SEX_QUERY_PARAM, defaultValue = ".+", required = false) sex: String,
        @RequestParam(POSITION_QUERY_PARAM, defaultValue = ".+", required = false) position: String,
        @RequestParam(STATUS_QUERY_PARAM, defaultValue = ".+", required = false) status: String,
        @RequestParam(START_AGE_QUERY_PARAM, defaultValue = "0", required = false) startAge: Int,
        @RequestParam(END_AGE_QUERY_PARAM, defaultValue = "100", required = false) endAge: Int,
        @PageableDefault(page = 0, size = 20) pageable: Pageable,
    ) = personService.extendedGet(name, surname, patronymic, sex, position, status, startAge, endAge, pageable)

    @PutMapping(
        path = [PERSON_ID_URL_PATH, CONTACTS_URL_PATH],
        consumes = [APPLICATION_JSON_VALUE],
        produces = [APPLICATION_JSON_VALUE],
    )
    suspend fun editContacts(
        @PathVariable personId: String,
        @Valid @RequestBody contactsDto: ContactsDto,
    ) = personService.editContacts(personId, contactsDto)

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
        private const val ALL_URL_PATH = "all"
        private const val EXTENDED_URL_PATH = "extended"
        private const val CONTACTS_URL_PATH = "contacts"
        private const val NAME_QUERY_PARAM = "name"
        private const val SURNAME_QUERY_PARAM = "surname"
        private const val PATRONYMIC_QUERY_PARAM = "patronymic"
        private const val SEX_QUERY_PARAM = "sex"
        private const val POSITION_QUERY_PARAM = "position"
        private const val STATUS_QUERY_PARAM = "status"
        private const val START_AGE_QUERY_PARAM = "start_age"
        private const val END_AGE_QUERY_PARAM = "end_age"
    }
}