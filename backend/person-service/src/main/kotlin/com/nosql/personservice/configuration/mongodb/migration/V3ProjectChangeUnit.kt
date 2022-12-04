package com.nosql.personservice.configuration.mongodb.migration

import com.nosql.personservice.configuration.mongodb.migration.V3ProjectChangeUnit.Companion.CHANGE_UNIT_ID
import com.nosql.personservice.configuration.mongodb.migration.V3ProjectChangeUnit.Companion.CHANGE_UNIT_ORDER
import com.nosql.personservice.constants.mongock.CHANGE_UNIT_AUTHOR
import com.nosql.personservice.entity.ProjectEntity
import io.mongock.api.annotations.ChangeUnit
import io.mongock.api.annotations.Execution
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.MongoTemplate

@ChangeUnit(id = CHANGE_UNIT_ID, order = CHANGE_UNIT_ORDER, author = CHANGE_UNIT_AUTHOR)
class V3ProjectChangeUnit(
    private val mongoTemplate: MongoTemplate,
) : AbstractChangeUnit(CHANGE_UNIT_ID, CHANGE_UNIT_ORDER) {

    @Execution
    override fun execute() {
        val projects = buildProjects()
        mongoTemplate.insertAll(projects)
    }

    private fun buildProjects() =
        listOf(
            buildGithubGistProject(),
            buildAmazonCognitoProject(),
            buildHospitalProject(),
            buildVacationProject(),
            buildLegalSimple(),
        )

    private fun buildGithubGistProject() =
        ProjectEntity(
            id = ObjectId(GITHUB_GIST_PROJECT_ID),
            name = "Github Gist"
        )

    private fun buildAmazonCognitoProject() =
        ProjectEntity(
            id = ObjectId(AMAZON_COGNITO_PROJECT_ID),
            name = "Amazon Cognito"
        )

    private fun buildHospitalProject() =
        ProjectEntity(
            id = ObjectId(HOSPITAL_PROJECT_ID),
            name = "Больничный"
        )

    private fun buildVacationProject() =
        ProjectEntity(
            id = ObjectId(VACATION_PROJECT_ID),
            name = "Отпуск"
        )

    private fun buildLegalSimple() =
        ProjectEntity(
            id = ObjectId(LEGAL_SIMPLE_PROJECT_ID),
            name = "Легальный простой"
        )

    companion object {

        const val CHANGE_UNIT_ORDER = "3"
        const val CHANGE_UNIT_ID = "$CHANGE_UNIT_AUTHOR-$CHANGE_UNIT_ORDER"

        const val GITHUB_GIST_PROJECT_ID = "6359c20fea6757775ceca286"
        const val AMAZON_COGNITO_PROJECT_ID = "638c910b13401bc9252e9d0a"
        const val HOSPITAL_PROJECT_ID = "638c8f6c4076bb8ce10a1d52"
        const val VACATION_PROJECT_ID = "638c8fdf5398a82b45a664fa"
        const val LEGAL_SIMPLE_PROJECT_ID = "638c911022fe2dfca0b16ace"

        val DEFAULT_PROJECT_IDS = listOf(HOSPITAL_PROJECT_ID, VACATION_PROJECT_ID, LEGAL_SIMPLE_PROJECT_ID)
    }
}
