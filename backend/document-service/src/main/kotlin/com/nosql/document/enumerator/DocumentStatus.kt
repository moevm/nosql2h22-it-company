package com.nosql.document.enumerator

enum class DocumentStatus {
    ORDERED,
    IN_PROGRESS,
    DONE,
    CANCELED;

    companion object {
        fun isMember(type: String) = values().any { it.name == type.uppercase() }

        fun get(type: String) = values().first { it.name == type.uppercase() }
    }
}