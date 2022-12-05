package com.nosql.personservice.enumerator

enum class SexEnum {
    MALE,
    FEMALE;

    companion object {

        fun isMember(sex: String) = values().any { it.name == sex.uppercase() }

        fun get(sex: String) = values().first { it.name == sex.uppercase() }
    }
}