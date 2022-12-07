package com.nosql.watcher.service.impl.validation

import com.nosql.watcher.common.logger.logger
import org.slf4j.Logger

abstract class  Validator<T> {

    private var nextValidator: Validator<T>? = null
    protected val log: Logger by logger()

    open suspend fun isValid(model: T): Boolean {
        return if (this.nextValidator != null) this.nextValidator!!.isValid(model) else true
    }

    open fun setNextValidator(validator: Validator<T>) {
        this.nextValidator = validator
    }

}