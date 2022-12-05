package com.nosql.personservice.service.validation.project.validator.configuration

import com.nosql.personservice.dto.export_import.project.ImportProjectDto
import com.nosql.personservice.service.validation.DefaultValidatorChainBuilder
import com.nosql.personservice.service.validation.project.validator.DefaultIdValidator
import com.nosql.personservice.service.validation.project.validator.DefaultNameValidator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ProjectValidatorConfig {

    @Bean
    fun getProjectValidatorChain() = DefaultValidatorChainBuilder<ImportProjectDto>()
        .add(DefaultIdValidator())
        .add(DefaultNameValidator())
}