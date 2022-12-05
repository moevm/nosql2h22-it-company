package com.nosql.personservice.service.validation.office.validator.configuration

import com.nosql.personservice.dto.export_import.office.ImportOfficeDto
import com.nosql.personservice.service.validation.office.validator.DefaultAddressValidator
import com.nosql.personservice.service.validation.office.validator.DefaultIdValidator
import com.nosql.personservice.service.validation.office.validator.DefaultNameValidator
import com.nosql.personservice.service.validation.DefaultValidatorChainBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OfficeValidatorConfig {

    @Bean
    fun getOfficeValidatorChain() = DefaultValidatorChainBuilder<ImportOfficeDto>()
        .add(DefaultIdValidator())
        .add(DefaultNameValidator())
        .add(DefaultAddressValidator())

}