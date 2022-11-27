package com.nosql.document.common.exception.handler

import com.nosql.document.common.exception.dto.ApplicationErrorDto
import com.nosql.document.common.exception.enumerator.ApplicationExceptionCodeEnum.DECODING_EXCEPTION
import com.nosql.document.common.exception.enumerator.ApplicationExceptionCodeEnum.VALIDATION_EXCEPTION
import com.nosql.document.common.exception.ApplicationException
import com.nosql.document.common.logger.logger
import org.slf4j.Logger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.support.WebExchangeBindException
import org.springframework.web.server.ServerWebInputException
import java.util.StringJoiner
import java.util.stream.Collectors

@ControllerAdvice
class ExceptionHandler {

    private val log: Logger by logger()

    @ExceptionHandler(ApplicationException::class)
    fun handleCommonException(exception: ApplicationException): ResponseEntity<ApplicationErrorDto> {

        val errorDto = ApplicationErrorDto(exception)
        val status = exception.status

        log.error("Got application exception. Response: $errorDto")

        return ResponseEntity
            .status(status ?: HttpStatus.INTERNAL_SERVER_ERROR)
            .body(errorDto)
    }

    @ExceptionHandler(WebExchangeBindException::class)
    fun handleException(exception: WebExchangeBindException): ResponseEntity<ApplicationErrorDto>? {

        val errorMessageJoiner = StringJoiner(ERROR_MESSAGE_DELIMITER)
        val bindingResult = exception.bindingResult

        if (bindingResult.hasGlobalErrors()) {

            val globalErrorsMessages = bindingResult.globalErrors.stream()
                .map { "${it.objectName}: ${it.defaultMessage}" }
                .collect(Collectors.joining(ERROR_MESSAGE_DELIMITER))

            errorMessageJoiner.add(globalErrorsMessages)
        }

        if (bindingResult.hasFieldErrors()) {

            val fieldErrorMessages = bindingResult.fieldErrors.stream()
                .map { "${it.field}: ${it.defaultMessage}" }
                .collect(Collectors.joining(ERROR_MESSAGE_DELIMITER))

            errorMessageJoiner.add(fieldErrorMessages)
        }

        val errorDto = ApplicationErrorDto(
            code = VALIDATION_EXCEPTION.name,
            description = errorMessageJoiner.toString(),
        )

        log.error("Got validation exception. Response: $errorDto", exception)

        return ResponseEntity
            .badRequest()
            .body(errorDto)
    }

    @ExceptionHandler(ServerWebInputException::class)
    fun handleException(exception: ServerWebInputException): ResponseEntity<ApplicationErrorDto> {
        val errorDto = ApplicationErrorDto(
            code = DECODING_EXCEPTION.name,
            description = getBadlyDecodedFieldMessage(exception.cause?.message)
        )
        log.warn("Got decoding exception. Response: {}", errorDto, exception)

        return ResponseEntity
            .badRequest()
            .body(errorDto)
    }

    @ExceptionHandler(Exception::class)
    fun handleUnexpectedException(exception: Exception): ResponseEntity<ApplicationErrorDto> {

        val apiErrorDto = ApplicationErrorDto(exception)

        log.error("Got unexpected application error. Response: {}", apiErrorDto, exception)

        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(apiErrorDto)
    }

    companion object {

        private const val ERROR_MESSAGE_DELIMITER = "; "
        private const val REGEX_FOR_BADLY_DECODED_FIELD = "\"[A-z\\d-]*\""

        private fun getBadlyDecodedFieldMessage(message: String?): String {
            val badlyDecodedField = getBadlyDecodedField(message)
            return "Value $badlyDecodedField is incorrect"
        }

        private fun getBadlyDecodedField(message: String?): String {
            val regex = REGEX_FOR_BADLY_DECODED_FIELD.toRegex()
            val matchResult = message?.let { regex.find(it) }
            return matchResult?.value?.replace('"', ' ', false) ?: "unknown"
        }
    }
}
