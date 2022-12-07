package com.nosql.document.enumerator

enum class DocumentType {

    INCOME_STATEMENT,
    WORK_STATEMENT;

    companion object {

        fun isMember(type: String) = values().any { it.name == type.uppercase() }

        fun get(type: String) = values().first { it.name == type.uppercase() }
    }
}