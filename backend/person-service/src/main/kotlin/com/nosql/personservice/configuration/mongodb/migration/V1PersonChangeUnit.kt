package com.nosql.personservice.configuration.mongodb.migration

import com.nosql.personservice.configuration.mongodb.migration.V1PersonChangeUnit.Companion.CHANGE_UNIT_ID
import com.nosql.personservice.configuration.mongodb.migration.V1PersonChangeUnit.Companion.CHANGE_UNIT_ORDER
import com.nosql.personservice.configuration.mongodb.migration.V2OfficeChangeUnit.Companion.MOSCOW_OFFICE_ID
import com.nosql.personservice.configuration.mongodb.migration.V2OfficeChangeUnit.Companion.SPB_OFFICE_ID
import com.nosql.personservice.configuration.mongodb.migration.V3ProjectChangeUnit.Companion.AMAZON_COGNITO_PROJECT_ID
import com.nosql.personservice.configuration.mongodb.migration.V3ProjectChangeUnit.Companion.DEFAULT_PROJECT_IDS
import com.nosql.personservice.configuration.mongodb.migration.V3ProjectChangeUnit.Companion.GITHUB_GIST_PROJECT_ID
import com.nosql.personservice.constants.mongock.CHANGE_UNIT_AUTHOR
import com.nosql.personservice.dto.ConfidentialDto
import com.nosql.personservice.dto.ContactsDto
import com.nosql.personservice.dto.JobTimeDto
import com.nosql.personservice.dto.PassportDataDto
import com.nosql.personservice.dto.PersonDto
import com.nosql.personservice.entity.PersonEntity
import com.nosql.personservice.enumerator.PositionEnum
import com.nosql.personservice.enumerator.SexEnum
import com.nosql.personservice.enumerator.StatusEnum
import com.nosql.personservice.util.convert
import io.mongock.api.annotations.ChangeUnit
import io.mongock.api.annotations.Execution
import org.springframework.core.convert.ConversionService
import org.springframework.data.mongodb.core.MongoTemplate
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

@ChangeUnit(id = CHANGE_UNIT_ID, order = CHANGE_UNIT_ORDER, author = CHANGE_UNIT_AUTHOR)
class V1PersonChangeUnit(
    private val mongoTemplate: MongoTemplate,
    private val conversionService: ConversionService,
) : AbstractChangeUnit(CHANGE_UNIT_ID, CHANGE_UNIT_ORDER) {

    @Execution
    override fun execute() {
        val persons = buildPersons()
        mongoTemplate.insertAll(persons)
    }

    private fun buildPersons() =
        listOf(
            buildHRPerson(),
            buildDeveloperPerson(),
            buildProjectManagerPerson(),
        ).map { conversionService.convert(it, PersonEntity::class) }

    private fun buildHRPerson() =
        PersonDto(
            id = HR_PERSON_ID,
            name = "Екатерина",
            surname = "Аксёнова",
            patronymic = "Александровна",
            sex = SexEnum.FEMALE,
            birthday = toDate("2001-11-14"),
            firstWorkDate = toDate("2022-04-14"),
            position = PositionEnum.HR,
            status = StatusEnum.WORKING,
            contacts = ContactsDto(
                phoneNumber = "79112886438",
                email = "kate.kate6575@gmail.com",
            ),
            jobTime = JobTimeDto(
                start = "11:00",
                end = "20:00"
            ),
            officeId = SPB_OFFICE_ID,
            confidential = ConfidentialDto(
                passportData = PassportDataDto(
                    number = "BM5653789",
                    issuedPlace = "Октябрьский РОВД",
                    issuedDate = toDate("2015-10-14"),
                ),
                nationality = "BELARUS",
                address = "Улица Торжковская, дом 15",
                salary = 1000000,
                projectIds = DEFAULT_PROJECT_IDS + listOf(GITHUB_GIST_PROJECT_ID)
            ),
            comment = "Комментарий для Екатерины",
        )

    private fun toDate(date: String): Date =
        Date.from(LocalDate.parse(date).atStartOfDay(ZoneId.systemDefault()).toInstant())

    private fun buildDeveloperPerson() =
        PersonDto(
            id = DEVELOPER_PERSON_ID,
            name = "Мария",
            surname = "Иванова",
            patronymic = "Александровна",
            sex = SexEnum.FEMALE,
            birthday = toDate("1999-11-05"),
            firstWorkDate = toDate("2020-07-14"),
            position = PositionEnum.MIDDLE_FRONTEND_DEVELOPER,
            status = StatusEnum.WORKING,
            contacts = ContactsDto(
                phoneNumber = "79118123818",
                email = "maria.ivanova@gmail.com",
            ),
            jobTime = JobTimeDto(
                start = "09:00",
                end = "18:00"
            ),
            officeId = SPB_OFFICE_ID,
            confidential = ConfidentialDto(
                passportData = PassportDataDto(
                    number = "2929291",
                    issuedPlace = "МВД России",
                    issuedDate = toDate("2011-07-11"),
                ),
                nationality = "RUSSIA",
                address = "Улица Ленина, дом 1",
                salary = 2500000,
                projectIds = DEFAULT_PROJECT_IDS + listOf(GITHUB_GIST_PROJECT_ID)
            ),
            comment = "Комментарий для Марии",
        )

    private fun buildProjectManagerPerson() =
        PersonDto(
            id = PM_PERSON_ID,
            name = "Александр",
            surname = "Попов",
            patronymic = "Дмитриевич",
            sex = SexEnum.MALE,
            birthday = toDate("1998-01-15"),
            firstWorkDate = toDate("2021-09-16"),
            position = PositionEnum.PM,
            status = StatusEnum.WORKING,
            contacts = ContactsDto(
                phoneNumber = "79218323118",
                email = "alexandr.popov@gmail.com",
            ),
            jobTime = JobTimeDto(
                start = "08:00",
                end = "17:00"
            ),
            officeId = MOSCOW_OFFICE_ID,
            confidential = ConfidentialDto(
                passportData = PassportDataDto(
                    number = "6911297",
                    issuedPlace = "МВД России",
                    issuedDate = toDate("2005-12-31"),
                ),
                nationality = "RUSSIA",
                address = "Улица Сердобольская, дом 152",
                salary = 1800000,
                projectIds = DEFAULT_PROJECT_IDS + listOf(AMAZON_COGNITO_PROJECT_ID)
            ),
            comment = "Комментарий для Александра",
        )

    companion object {

        const val CHANGE_UNIT_ORDER = "1"
        const val CHANGE_UNIT_ID = "$CHANGE_UNIT_AUTHOR-$CHANGE_UNIT_ORDER"

        const val HR_PERSON_ID = "638b9e21fe9cfa34d93be93d"
        const val DEVELOPER_PERSON_ID = "638b9e21fe9cfa34d93be93b"
        const val PM_PERSON_ID = "638b9e21fe9cfa34d93be93f"
    }
}
