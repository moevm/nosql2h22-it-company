package com.nosql.authservice.service

import com.nimbusds.jose.jwk.JWKSet
import java.security.KeyPair

interface JwksService {

    fun getKeyPair(): KeyPair
    fun getJwks(): JWKSet
    fun getKeyId(): String
}
