package com.nosql.document.factory

import com.nosql.document.entity.SimpleCheckEntity

class SimpleCheckTestDataFactory {

    fun buildSimpleCheckEntity() = SimpleCheckEntity(id = ID, message = HELLO_MESSAGE, userName = NAME)

    fun buildUpdatedSimpleCheckEntity() = SimpleCheckEntity(id = ID, message = UPDATED_HELLO_MESSAGE, userName = NAME)

    companion object {
        const val ID = "ad06c686-514b-4db9-8923-9206afb3fc64"
        private const val HELLO_MESSAGE = "Hello world!"
        private const val UPDATED_HELLO_MESSAGE = "Hello, my friend!"
        private const val NAME = "Kate"
    }
}