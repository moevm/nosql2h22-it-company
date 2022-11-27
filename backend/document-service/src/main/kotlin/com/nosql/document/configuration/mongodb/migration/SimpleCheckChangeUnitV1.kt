package com.nosql.document.configuration.mongodb.migration

import com.nosql.document.configuration.mongodb.migration.AbstractChangeUnit.Companion.CHANGE_UNIT_AUTHOR
import com.nosql.document.configuration.mongodb.migration.SimpleCheckChangeUnitV1.Companion.CHANGE_UNIT_PARAMETER
import com.nosql.document.entity.SimpleCheckEntity
import io.mongock.api.annotations.ChangeUnit
import io.mongock.api.annotations.Execution
import org.springframework.data.mongodb.core.MongoTemplate

@ChangeUnit(id = CHANGE_UNIT_PARAMETER, order = CHANGE_UNIT_PARAMETER, author = CHANGE_UNIT_AUTHOR)
class SimpleCheckChangeUnitV1(
    private val mongoTemplate: MongoTemplate,
) : AbstractChangeUnit(CHANGE_UNIT_PARAMETER) {

    @Execution
    override fun execute() {
        val objects = listOf(
            SimpleCheckEntity(message = "Example Message 1", userName = "Example User Name 1"),
            SimpleCheckEntity(message = "Example Message 2", userName = "Example User Name 2"),
        )
        mongoTemplate.insertAll(objects)
    }

    companion object {

        const val CHANGE_UNIT_PARAMETER = "1"
    }
}
