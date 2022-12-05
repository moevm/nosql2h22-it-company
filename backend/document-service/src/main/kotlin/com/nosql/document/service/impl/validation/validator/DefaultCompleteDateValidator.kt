package com.nosql.document.service.impl.validation.validator

import com.nosql.document.dto.ImportDocumentDto
import com.nosql.document.service.impl.validation.Validator
import java.text.SimpleDateFormat
import java.util.regex.Pattern

class DefaultCompleteDateValidator: Validator<ImportDocumentDto>() {


    override suspend fun isValid(model: ImportDocumentDto): Boolean {
        if (
            model.completeDate != null && (!Pattern.matches(DATE_REGEX, model.completeDate) ||
            dateFormatter.parse(model.completeDate).before(dateFormatter.parse(model.orderDate)))
        ) {
            log.warn("Wrong completeDate for record with id = '${model.id}'")
            return false
        }
        return super.isValid(model)
    }

    companion object {
        private const val DATE_REGEX = "(202\\d)-(0[1-9]|1[0-2])-(0[1-9]|[1-2]\\d|3[0-1])"
        private val dateFormatter = SimpleDateFormat("yyyy-MM-dd")
    }
}