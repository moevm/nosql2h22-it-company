package com.nosql.personservice.common.auth

/**
 * Annotation for [UserAuthInfo] object in controller.
 *
 * @see [UserAuthInfoArgumentResolver]
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class UserAuth(
    /**
     * Is [UserAuthInfo] required for execution
     */
    val required: Boolean = true,
)
