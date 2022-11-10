package com.nosql.authservice.configuration.mongodb.migration

import com.nosql.authservice.configuration.mongodb.migration.AbstractChangeUnit.Companion.CHANGE_UNIT_AUTHOR
import com.nosql.authservice.configuration.mongodb.migration.UserChangeUnitV2.Companion.CHANGE_UNIT_PARAMETER
import com.nosql.authservice.dto.UserDto
import com.nosql.authservice.entity.UserEntity
import com.nosql.authservice.util.convert
import io.mongock.api.annotations.ChangeUnit
import io.mongock.api.annotations.Execution
import org.springframework.core.convert.ConversionService
import org.springframework.data.mongodb.core.MongoTemplate

@ChangeUnit(id = CHANGE_UNIT_PARAMETER, order = CHANGE_UNIT_PARAMETER, author = CHANGE_UNIT_AUTHOR)
class UserChangeUnitV2(
    private val mongoTemplate: MongoTemplate,
    private val conversionService: ConversionService,
) : AbstractChangeUnit(CHANGE_UNIT_PARAMETER) {

    @Execution
    override fun execute() {
        val objects = listOf(
            UserDto(login = "alexey", password = "alexey"),
            UserDto(login = "kate", password = "kate"),
            UserDto(login = "vladimir", password = "vladimir"),
        ).map { conversionService.convert(it, UserEntity::class) }

        mongoTemplate.insertAll(objects)
    }

    companion object {

        const val CHANGE_UNIT_PARAMETER = "2"
    }
}
