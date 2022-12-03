package com.nosql.document.mapper

import com.nosql.document.dto.StatusDto
import com.nosql.document.entity.DocumentEntity
import com.nosql.document.enumerator.DocumentStatus
import com.nosql.document.enumerator.DocumentStatus.DONE
import java.time.Instant
import java.util.Date

fun DocumentEntity.merge(status: StatusDto) {
    val newStatus = status.status
    this.status = newStatus
    defineCompleteDateWhenCompleteStatus(newStatus)

}

private fun DocumentEntity.defineCompleteDateWhenCompleteStatus(status: DocumentStatus) {
    if (status == DONE) {
        completeDate = Date.from(Instant.now())
    }
}
