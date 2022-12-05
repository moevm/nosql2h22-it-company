package com.nosql.personservice.enumerator

enum class StatusEnum {
    WORKING,
    ON_HOLIDAY,
    SEEK_LEAVE,
    NOT_WORKING;

    companion object {

        fun isMember(sex: String) = values().any { it.name == sex.uppercase() }

        fun get(status: String) = values().first { it.name == status.uppercase() }
    }
}