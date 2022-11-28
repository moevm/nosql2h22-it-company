package com.nosql.document.common.auth

import com.nimbusds.jwt.SignedJWT
import org.springframework.lang.NonNull

open class UserAuthInfo(

    @NonNull
    val userId: String,
) {
    constructor(jwt: SignedJWT) : this(
        userId = jwt.jwtClaimsSet.subject,
    )
}
