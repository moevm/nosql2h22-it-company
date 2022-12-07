package com.nosql.document.service.impl.validation.validator

import com.nosql.document.dto.ImportDocumentDto
import com.nosql.document.service.impl.validation.Validator
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Date
import java.util.regex.Pattern

class DefaultOrderDateValidator: Validator<ImportDocumentDto>() {


    override suspend fun isValid(model: ImportDocumentDto): Boolean {
        if (
            model.orderDate == null || !Pattern.matches(DATE_REGEX, model.orderDate) ||
            dateFormatter.parse(model.orderDate).after(Date.from(Instant.now()))
        ) {
            log.warn("Wrong completeDate for record with id = '${model.id}'")
            return false
        }
        return super.isValid(model)
    }

    companion object {
        private const val DATE_REGEX = "(20\\d\\d)-(0[1-9]|1[0-2])-(0[1-9]|[1-2]\\d|3[0-1])"
        private val dateFormatter = SimpleDateFormat("yyyy-MM-dd")
    }
}