package com.nosql.personservice.service.validation.person.validator.configuration

import com.nosql.personservice.dto.export_import.person.ImportPersonDto
import com.nosql.personservice.repository.OfficeRepository
import com.nosql.personservice.repository.ProjectRepository
import com.nosql.personservice.repository.UserRepository
import com.nosql.personservice.service.validation.person.validator.DefaultBirthdayValidator
import com.nosql.personservice.service.validation.person.validator.DefaultConfidentialValidator
import com.nosql.personservice.service.validation.person.validator.DefaultContactsValidator
import com.nosql.personservice.service.validation.person.validator.DefaultFirstWorkDayValidator
import com.nosql.personservice.service.validation.person.validator.DefaultIdValidator
import com.nosql.personservice.service.validation.person.validator.DefaultJobTimeValidator
import com.nosql.personservice.service.validation.person.validator.DefaultNameValidator
import com.nosql.personservice.service.validation.person.validator.DefaultOfficeValidator
import com.nosql.personservice.service.validation.person.validator.DefaultPassportDataValidator
import com.nosql.personservice.service.validation.person.validator.DefaultPatronymicValidator
import com.nosql.personservice.service.validation.person.validator.DefaultPositionValidator
import com.nosql.personservice.service.validation.person.validator.DefaultProjectIdsValidator
import com.nosql.personservice.service.validation.person.validator.DefaultSexValidator
import com.nosql.personservice.service.validation.person.validator.DefaultStatusValidator
import com.nosql.personservice.service.validation.person.validator.DefaultSurnameValidator
import com.nosql.personservice.service.validation.DefaultValidatorChainBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PersonValidatorConfig {

    @Bean
    fun getPersonValidatorChain(
        userRepository: UserRepository,
        officeRepository: OfficeRepository,
        projectRepository: ProjectRepository,
    ) = DefaultValidatorChainBuilder<ImportPersonDto>()
        .add(DefaultIdValidator(userRepository))
        .add(DefaultNameValidator())
        .add(DefaultSurnameValidator())
        .add(DefaultPatronymicValidator())
        .add(DefaultSexValidator())
        .add(DefaultBirthdayValidator())
        .add(DefaultFirstWorkDayValidator())
        .add(DefaultPositionValidator())
        .add(DefaultStatusValidator())
        .add(DefaultOfficeValidator(officeRepository))
        .add(DefaultContactsValidator())
        .add(DefaultJobTimeValidator())
        .add(DefaultConfidentialValidator())
        .add(DefaultPassportDataValidator())
        .add(DefaultProjectIdsValidator(projectRepository))
}