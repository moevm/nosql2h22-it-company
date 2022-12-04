package com.nosql.authservice.configuration.mongodb.migration

import com.nosql.authservice.configuration.mongodb.migration.V1UserChangeUnit.Companion.CHANGE_UNIT_ID
import com.nosql.authservice.configuration.mongodb.migration.V1UserChangeUnit.Companion.CHANGE_UNIT_ORDER
import com.nosql.authservice.constants.mongock.CHANGE_UNIT_AUTHOR
import com.nosql.authservice.dto.SignUpRequestDto
import com.nosql.authservice.entity.UserEntity
import com.nosql.authservice.enumerator.Role
import com.nosql.authservice.util.convert
import io.mongock.api.annotations.ChangeUnit
import io.mongock.api.annotations.Execution
import org.bson.types.ObjectId
import org.springframework.core.convert.ConversionService
import org.springframework.data.mongodb.core.MongoTemplate

@ChangeUnit(id = CHANGE_UNIT_ID, order = CHANGE_UNIT_ORDER, author = CHANGE_UNIT_AUTHOR)
class V1UserChangeUnit(
    private val mongoTemplate: MongoTemplate,
    private val conversionService: ConversionService,
) : AbstractChangeUnit(CHANGE_UNIT_ID, CHANGE_UNIT_ORDER) {

    @Execution
    override fun execute() {
        val users = buildUsers()
        mongoTemplate.insertAll(users)
    }

    private fun buildUsers() =
        listOf(Role.HR, Role.DEVELOPER, Role.PROJECT_MANAGER)
            .map { buildUser(it) }
            .map { conversionService.convert(it, UserEntity::class) }
            .map { addUserId(it) }

    private fun buildUser(role: Role) =
        SignUpRequestDto(
            login = role.name.lowercase(),
            password = role.name.lowercase(),
            role = role,
        )

    private fun addUserId(user: UserEntity) =
        user.apply { id = resolveUserIdByRole(role) }

    private fun resolveUserIdByRole(role: Role?) =
        when (role) {
            Role.HR -> HR_USER_ID
            Role.DEVELOPER -> DEVELOPER_USER_ID
            Role.PROJECT_MANAGER -> PROJECT_MANAGER_USER_ID
            else -> null
        }?.let { ObjectId(it) }

    companion object {

        const val CHANGE_UNIT_ORDER = "1"
        const val CHANGE_UNIT_ID = "$CHANGE_UNIT_AUTHOR-$CHANGE_UNIT_ORDER"

        const val HR_USER_ID = "638b9e21fe9cfa34d93be93d"
        const val DEVELOPER_USER_ID = "638b9e21fe9cfa34d93be93b"
        const val PROJECT_MANAGER_USER_ID = "638b9e21fe9cfa34d93be93f"
    }
}
