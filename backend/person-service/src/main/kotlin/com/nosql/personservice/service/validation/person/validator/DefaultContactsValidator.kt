package com.nosql.personservice.service.validation.person.validator

import com.nosql.personservice.dto.export_import.person.ImportPersonDto
import com.nosql.personservice.service.validation.Validator
import java.util.regex.Pattern

class DefaultContactsValidator: Validator<ImportPersonDto>() {


    override suspend fun isValid(model: ImportPersonDto): Boolean {
        if (
            model.contacts == null || model.contacts!!.email == null || model.contacts!!.phoneNumber == null ||
            !Pattern.matches(EMAIL_REGEX, model.contacts!!.email) ||
            !Pattern.matches(PHONE_REGEX, model.contacts!!.phoneNumber)
        ) {
            log.warn("Wrong contacts for record with id = '${model.id}'")
            return false
        }
        return super.isValid(model);
    }

    companion object {
        private const val EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$"
        private const val PHONE_REGEX = "[0-9]{7,20}"
    }
}