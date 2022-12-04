package com.nosql.personservice.configuration.mongodb.migration

interface ChangeUnit {

    fun execute()

    fun rollback()
}
