package com.nosql.watcher.configuration.mongodb.migration

import com.nosql.watcher.configuration.mongodb.migration.V1WatcherChangeUnit.Companion.CHANGE_UNIT_ID
import com.nosql.watcher.configuration.mongodb.migration.V1WatcherChangeUnit.Companion.CHANGE_UNIT_ORDER
import com.nosql.watcher.constants.mongock.CHANGE_UNIT_AUTHOR
import com.nosql.watcher.entity.WatcherEntity
import io.mongock.api.annotations.ChangeUnit
import io.mongock.api.annotations.Execution
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.MongoTemplate
import java.time.Duration
import java.time.Instant
import java.util.Date

@ChangeUnit(id = CHANGE_UNIT_ID, order = CHANGE_UNIT_ORDER, author = CHANGE_UNIT_AUTHOR)
class V1WatcherChangeUnit(
    private val mongoTemplate: MongoTemplate,
) : AbstractChangeUnit(CHANGE_UNIT_ID, CHANGE_UNIT_ORDER) {

    @Execution
    override fun execute() {
        val watcher = buildWatcherRecords()
        mongoTemplate.insertAll(watcher)
    }

    private fun buildWatcherRecords(): List<WatcherEntity> {
        val now = Instant.now()
        var leftBound = now - Duration.ofDays(TWO_WEEKS)
        val rightBound = now + Duration.ofDays(TWO_WEEKS)
        val watcherRecords = mutableListOf<WatcherEntity>()

        while (leftBound < rightBound) {
            leftBound += Duration.ofDays(ONE_DAY)

            val date = Date.from(leftBound)
            watcherRecords += listOf(
                buildWatcher(HR_USER_ID, GITHUB_GIST_PROJECT_ID, date),
                buildWatcher(DEVELOPER_USER_ID, VACATION_PROJECT_ID, date),
                buildWatcher(PROJECT_MANAGER_USER_ID, AMAZON_COGNITO_PROJECT_ID, date),
            )
        }

        return watcherRecords
    }

    private fun buildWatcher(userId: String, projectId: String, date: Date) =
        WatcherEntity(
            userId = ObjectId(userId),
            date = date,
            projectId = ObjectId(projectId),
            minutesAmount = FULL_DAY,
            comment = "Синки весь день. Решение проблем на проде.",
        )

    companion object {

        const val CHANGE_UNIT_ORDER = "1"
        const val CHANGE_UNIT_ID = "$CHANGE_UNIT_AUTHOR-$CHANGE_UNIT_ORDER"

        private const val HR_USER_ID = "638b9e21fe9cfa34d93be93d"
        private const val DEVELOPER_USER_ID = "638b9e21fe9cfa34d93be93b"
        private const val PROJECT_MANAGER_USER_ID = "638b9e21fe9cfa34d93be93f"

        private const val GITHUB_GIST_PROJECT_ID = "6359c20fea6757775ceca286"
        private const val AMAZON_COGNITO_PROJECT_ID = "638c910b13401bc9252e9d0a"
        private const val VACATION_PROJECT_ID = "638c8fdf5398a82b45a664fa"

        private const val FULL_DAY = 8L * 60
        private const val TWO_WEEKS = 14L
        private const val ONE_DAY = 1L
    }
}
