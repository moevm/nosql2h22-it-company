package com.nosql.authservice.common.auth

import io.jsonwebtoken.Claims
import org.springframework.lang.NonNull

/**
 * @see UserAuthInfoArgumentResolver
 */
open class UserAuthInfo(

    @NonNull
    val userId: String,
) {

    constructor(claims: Claims) : this(
        userId = claims.subject,
    )
}
