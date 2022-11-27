package com.nosql.document.configuration.mongodb.migration

interface ChangeUnit {

    fun execute()

    fun rollback()
}
