package com.nosql.watcher.configuration.mongodb.migration

interface ChangeUnit {

    fun execute()

    fun rollback()
}
