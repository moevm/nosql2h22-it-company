package com.nosql.authservice.configuration.mongodb.migration

interface ChangeUnit {

    fun execute()

    fun rollback()
}
