package com.nosql.authservice.configuration.mongodb.migration

import com.nosql.authservice.common.logger.logger
import io.mongock.api.annotations.RollbackExecution
import org.slf4j.Logger

abstract class AbstractChangeUnit(
    private val changeUnitId: String,
    private val changeUnitOrder: String,
) : ChangeUnit {

    private val log: Logger by logger()

    abstract override fun execute()

    @RollbackExecution
    override fun rollback() {
        log.error("Rollback: [id = $changeUnitId, order = $changeUnitOrder]")
    }
}
