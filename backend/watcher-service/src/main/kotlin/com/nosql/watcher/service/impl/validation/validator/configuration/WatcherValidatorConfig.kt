package com.nosql.watcher.service.impl.validation.validator.configuration

import com.nosql.watcher.dto.ImportWatcherDto
import com.nosql.watcher.repository.PersonRepository
import com.nosql.watcher.repository.ProjectRepository
import com.nosql.watcher.service.impl.validation.DefaultValidatorChainBuilder
import com.nosql.watcher.service.impl.validation.validator.DefaultCommentValidator
import com.nosql.watcher.service.impl.validation.validator.DefaultDateValidator
import com.nosql.watcher.service.impl.validation.validator.DefaultIdValidator
import com.nosql.watcher.service.impl.validation.validator.DefaultMinutesAmountValidator
import com.nosql.watcher.service.impl.validation.validator.DefaultProjectIdValidator
import com.nosql.watcher.service.impl.validation.validator.DefaultUserIdValidator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class WatcherValidatorConfig {

    @Bean
    fun getDocumentValidatorChain(
        personRepository: PersonRepository,
        projectRepository: ProjectRepository
    ) = DefaultValidatorChainBuilder<ImportWatcherDto>()
        .add(DefaultIdValidator())
        .add(DefaultUserIdValidator(personRepository))
        .add(DefaultProjectIdValidator(projectRepository))
        .add(DefaultMinutesAmountValidator())
        .add(DefaultDateValidator())
        .add(DefaultCommentValidator())
}