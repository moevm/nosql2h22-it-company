package com.nosql.document.mapper

import com.nosql.document.entity.DocumentEntity
import com.nosql.document.enumerator.DocumentStatus
import com.nosql.document.enumerator.DocumentStatus.DONE
import java.time.Instant
import java.util.Date

fun DocumentEntity.merge(document: DocumentEntity) {
    val newStatus = document.status
    status = newStatus
    defineCompleteDateWhenCompleteStatus(newStatus)

}

private fun DocumentEntity.defineCompleteDateWhenCompleteStatus(status: DocumentStatus) {
    if (status == DONE) {
        completeDate = Date.from(Instant.now())
    }
}
