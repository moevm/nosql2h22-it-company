package com.nosql.watcher.configuration.mongodb.migration

import com.nosql.watcher.common.logger.logger
import io.mongock.api.annotations.RollbackExecution
import org.slf4j.Logger

abstract class AbstractChangeUnit(
    private val changeUnitParameter: String,
) : ChangeUnit {

    private val log: Logger by logger()

    abstract override fun execute()

    @RollbackExecution
    override fun rollback() {
        log.error("Rollback: [id = $changeUnitParameter, order = $changeUnitParameter]")
    }

    companion object {
        const val CHANGE_UNIT_AUTHOR = "application"
    }
}
