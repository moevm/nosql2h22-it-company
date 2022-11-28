package com.nosql.document.common.auth

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class UserAuth(

    val required: Boolean = true
)
