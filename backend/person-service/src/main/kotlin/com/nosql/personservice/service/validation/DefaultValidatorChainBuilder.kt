package com.nosql.personservice.service.validation

class DefaultValidatorChainBuilder<T>(
    private var first: Validator<T>? = null,
    private var last: Validator<T>? = null,
) {

    fun add(validator: Validator<T>): DefaultValidatorChainBuilder<T> {
        if (this.first == null) {
            this.first = validator
        } else {
            this.last!!.setNextValidator(validator);
        }
        this.last = validator
        return this
    }

    fun getFirst() = this.first
}
