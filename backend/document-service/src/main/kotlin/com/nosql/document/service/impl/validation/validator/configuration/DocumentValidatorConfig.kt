package com.nosql.document.service.impl.validation.validator.configuration

import com.nosql.document.dto.ImportDocumentDto
import com.nosql.document.repository.PersonRepository
import com.nosql.document.service.impl.validation.DefaultValidatorChainBuilder
import com.nosql.document.service.impl.validation.validator.DefaultCompleteDateValidator
import com.nosql.document.service.impl.validation.validator.DefaultIdValidator
import com.nosql.document.service.impl.validation.validator.DefaultOrderDateValidator
import com.nosql.document.service.impl.validation.validator.DefaultStatusValidator
import com.nosql.document.service.impl.validation.validator.DefaultTypeValidator
import com.nosql.document.service.impl.validation.validator.DefaultUserIdValidator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DocumentValidatorConfig {

    @Bean
    fun getDocumentValidatorChain(
        personRepository: PersonRepository,
    ) = DefaultValidatorChainBuilder<ImportDocumentDto>()
        .add(DefaultIdValidator())
        .add(DefaultTypeValidator())
        .add(DefaultUserIdValidator(personRepository))
        .add(DefaultOrderDateValidator())
        .add(DefaultCompleteDateValidator())
        .add(DefaultStatusValidator())


}