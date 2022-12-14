package com.nosql.personservice.enumerator

enum class PositionEnum {
    JUNIOR_BACKEND_DEVELOPER,
    MIDDLE_BACKEND_DEVELOPER,
    SENIOR_BACKEND_DEVELOPER,
    JUNIOR_FRONTEND_DEVELOPER,
    MIDDLE_FRONTEND_DEVELOPER,
    SENIOR_FRONTEND_DEVELOPER,
    JUNIOR_QA_DEVELOPER,
    MIDDLE_QA_DEVELOPER,
    SENIOR_QA_DEVELOPER,
    JUNIOR_DEVOPS_DEVELOPER,
    MIDDLE_DEVOPS_DEVELOPER,
    SENIOR_DEVOPS_DEVELOPER,
    HR,
    PM,
    ADMINISTRATION,
    OFFICE_MANAGER;

    companion object {

        fun isMember(position: String) = values().any { it.name == position.uppercase() }

        fun get(position: String) = values().first { it.name == position.uppercase() }
    }
}