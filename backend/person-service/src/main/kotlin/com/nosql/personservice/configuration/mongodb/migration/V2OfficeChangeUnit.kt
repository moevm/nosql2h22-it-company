package com.nosql.personservice.configuration.mongodb.migration

import com.nosql.personservice.configuration.mongodb.migration.V2OfficeChangeUnit.Companion.CHANGE_UNIT_ID
import com.nosql.personservice.configuration.mongodb.migration.V2OfficeChangeUnit.Companion.CHANGE_UNIT_ORDER
import com.nosql.personservice.constants.mongock.CHANGE_UNIT_AUTHOR
import com.nosql.personservice.entity.OfficeEntity
import io.mongock.api.annotations.ChangeUnit
import io.mongock.api.annotations.Execution
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.MongoTemplate

@ChangeUnit(id = CHANGE_UNIT_ID, order = CHANGE_UNIT_ORDER, author = CHANGE_UNIT_AUTHOR)
class V2OfficeChangeUnit(
    private val mongoTemplate: MongoTemplate,
) : AbstractChangeUnit(CHANGE_UNIT_ID, CHANGE_UNIT_ORDER) {

    @Execution
    override fun execute() {
        val offices = buildOffices()
        mongoTemplate.insertAll(offices)
    }

    private fun buildOffices() =
        listOf(
            buildSpbOffice(),
            buildKalinaOffice(),
            buildMoscowOffice(),
        )

    private fun buildSpbOffice() =
        OfficeEntity(
            id = ObjectId(SPB_OFFICE_ID),
            name = "СПб-Офис",
            address = "г. Санкт-Петербург, ул. Марата, дом 1",
        )

    private fun buildKalinaOffice() =
        OfficeEntity(
            id = ObjectId(KALINA_OFFICE_ID),
            name = "Калина-Офис",
            address = "г. Калининград, ул. Джозефа, дом 17",
        )

    private fun buildMoscowOffice() =
        OfficeEntity(
            id = ObjectId(MOSCOW_OFFICE_ID),
            name = "Москва-Офис",
            address = "г. Москва, ул. Разеранд, дом 313",
        )


    companion object {

        const val CHANGE_UNIT_ORDER = "2"
        const val CHANGE_UNIT_ID = "$CHANGE_UNIT_AUTHOR-$CHANGE_UNIT_ORDER"

        const val SPB_OFFICE_ID = "6359c11c2416d454722f826f"
        const val KALINA_OFFICE_ID = "638c8cccdd19222f6b9457d6"
        const val MOSCOW_OFFICE_ID = "638ca77b8750e7b1cbb640c0"
    }
}
