package com.nosql.authservice.model

import com.nimbusds.jose.jwk.JWKSet
import java.security.KeyPair

data class JwksParameters(
    val keyPair: KeyPair,
    val jwkSet: JWKSet,
    val keyId: String,
)
