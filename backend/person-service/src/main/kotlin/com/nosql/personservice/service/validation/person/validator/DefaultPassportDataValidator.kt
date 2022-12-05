package com.nosql.personservice.service.validation.person.validator

import com.nosql.personservice.dto.export_import.person.ImportPersonDto
import com.nosql.personservice.service.validation.Validator
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Date
import java.util.regex.Pattern

class DefaultPassportDataValidator: Validator<ImportPersonDto>() {


    override suspend fun isValid(model: ImportPersonDto): Boolean {
        if (model.confidential!!.passportData == null ||
            model.confidential!!.passportData!!.number == null ||
            model.confidential!!.passportData!!.issuedDate == null ||
            model.confidential!!.passportData!!.issuedPlace == null ||
            !Pattern.matches(PASSPORT_NUMBER_REGEX, model.confidential!!.passportData!!.number) ||
            !Pattern.matches(DATE_REGEX, model.confidential!!.passportData!!.issuedDate) ||
            dateFormatter.parse(model.confidential!!.passportData!!.issuedDate).after(Date.from(Instant.now())) ||
            !Pattern.matches(ADDRESS_REGEX, model.confidential!!.passportData!!.issuedPlace)
        ) {
            log.warn("Wrong passportData for record with id = '${model.id}'")
            return false
        }
        return super.isValid(model)
    }

    companion object {
        private const val DATE_REGEX = "(20[0-1]\\d|202[0-2]|19[2-9]{2})-(0[1-9]|1[0-2])-(0[1-9]|[1-2]\\d|3[0-1])"
        private const val PASSPORT_NUMBER_REGEX = "[A-Za-z\\d]{5,20}"
        private const val ADDRESS_REGEX = "(\\p{InCyrillic}{1,20}\\.* *\\d*,* *){1,5}"
        private val dateFormatter = SimpleDateFormat("yyyy-MM-dd")
    }
}