package com.nosql.document.configuration.mongodb.migration

import com.nosql.document.configuration.mongodb.migration.V1DocumentChangeUnit.Companion.CHANGE_UNIT_ID
import com.nosql.document.configuration.mongodb.migration.V1DocumentChangeUnit.Companion.CHANGE_UNIT_ORDER
import com.nosql.document.constants.mongock.CHANGE_UNIT_AUTHOR
import com.nosql.document.entity.DocumentEntity
import com.nosql.document.enumerator.DocumentStatus
import com.nosql.document.enumerator.DocumentType
import io.mongock.api.annotations.ChangeUnit
import io.mongock.api.annotations.Execution
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.MongoTemplate
import java.time.Duration
import java.time.Instant
import java.util.Date
import kotlin.random.Random

@ChangeUnit(id = CHANGE_UNIT_ID, order = CHANGE_UNIT_ORDER, author = CHANGE_UNIT_AUTHOR)
class V1DocumentChangeUnit(
    private val mongoTemplate: MongoTemplate,
) : AbstractChangeUnit(CHANGE_UNIT_ID, CHANGE_UNIT_ORDER) {

    @Execution
    override fun execute() {
        val documents = buildDocuments()
        mongoTemplate.insertAll(documents)
    }

    private fun buildDocuments(): List<DocumentEntity> {
        val documentTypes = DocumentType.values().toList()
        val documentStatuses = DocumentStatus.values().toList()
        val userIds = listOf(HR_USER_ID, DEVELOPER_USER_ID, PROJECT_MANAGER_USER_ID)

        val now = Instant.now()
        val leftBound = now - Duration.ofDays(ONE_HUNDRED_DAYS)
        val instants = generateInstantRange(leftBound, now)

        val documents = userIds.flatMap { userId ->
            documentTypes.flatMap { documentType ->
                documentStatuses.map { documentStatus ->
                    DocumentEntity(
                        userId = ObjectId(userId),
                        type = documentType,
                        orderDate = Date.from(instants[Random.nextInt(MIN_INDEX, MAX_INDEX)]),
                        completeDate = resolveCompleteDateByDocumentStatus(documentStatus),
                        status = documentStatus,
                    )
                }
            }
        }

        return documents
    }

    private fun generateInstantRange(from: Instant, to: Instant): List<Instant> {
        var currentInstant = from
        val instants = mutableListOf<Instant>()

        while (currentInstant < to) {
            currentInstant += Duration.ofDays(ONE_DAY)
            instants.add(currentInstant)
        }

        return instants
    }

    private fun resolveCompleteDateByDocumentStatus(documentStatus: DocumentStatus) =
        if (documentStatus == DocumentStatus.DONE) {
            Date.from(Instant.now())
        } else {
            null
        }

    companion object {

        const val CHANGE_UNIT_ORDER = "1"
        const val CHANGE_UNIT_ID = "$CHANGE_UNIT_AUTHOR-$CHANGE_UNIT_ORDER"

        private const val HR_USER_ID = "638b9e21fe9cfa34d93be93d"
        private const val DEVELOPER_USER_ID = "638b9e21fe9cfa34d93be93b"
        private const val PROJECT_MANAGER_USER_ID = "638b9e21fe9cfa34d93be93f"

        private const val ONE_DAY = 1L
        private const val ONE_HUNDRED_DAYS = 100L
        private const val MIN_INDEX = 0
        private const val MAX_INDEX = 100
    }
}
