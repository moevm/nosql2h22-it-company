package com.nosql.authservice.service

import com.nimbusds.jose.jwk.JWKSet
import com.nosql.authservice.enumerator.TokenType
import java.security.KeyPair

interface JwksService {

    fun getKeyPair(tokenType: TokenType): KeyPair
    fun getJwks(tokenType: TokenType): JWKSet
    fun getKeyId(tokenType: TokenType): String
}
