package com.nosql.authservice.service.impl

import com.nimbusds.jose.JWSAlgorithm
import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.KeyUse
import com.nimbusds.jose.jwk.RSAKey
import com.nosql.authservice.configuration.properties.JwtProperties
import com.nosql.authservice.configuration.properties.KeystoreProperties
import com.nosql.authservice.enumerator.TokenType
import com.nosql.authservice.model.JwksParameters
import com.nosql.authservice.service.JwksService
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import java.security.KeyFactory
import java.security.KeyPair
import java.security.KeyStore
import java.security.cert.X509Certificate
import java.security.interfaces.RSAPrivateCrtKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.RSAPublicKeySpec

@Service
class DefaultJwksService(
    jwtProperties: JwtProperties,
) : JwksService {

    private lateinit var jwksParametersMap: Map<TokenType, JwksParameters>

    init {
        jwksParametersMap = mapOf(
            TokenType.ACCESS to generateJwksParameters(jwtProperties.accessToken.keystore),
            TokenType.REFRESH to generateJwksParameters(jwtProperties.refreshToken.keystore)
        )
    }

    private fun generateJwksParameters(keystore: KeystoreProperties): JwksParameters {
        val password = keystore.password.toCharArray()
        val store = loadKeystore(keystore.resourcePath, password)
        val alias = keystore.keypairAlias

        val keyPair = generateKeyPair(store, alias, password)

        return JwksParameters(
            keyPair = keyPair,
            jwkSet = generateJwks(store, alias, keyPair.public as RSAPublicKey),
            keyId = getX509CertificateSerialNumber(store, alias),
        )
    }

    private fun generateKeyPair(store: KeyStore, keystoreAlias: String, password: CharArray): KeyPair {
        val rsaPrivateKey = generatePrivateKey(store, keystoreAlias, password)
        val rsaPublicKey = generatePublicKey(rsaPrivateKey)

        return KeyPair(rsaPublicKey, rsaPrivateKey)
    }

    private fun loadKeystore(keystoreResourcePath: String, password: CharArray): KeyStore {
        val store = KeyStore.getInstance(JKS_KEY_STORE_TYPE)
        ClassPathResource(keystoreResourcePath).inputStream
            .let { store.load(it, password) }
        return store
    }

    private fun generatePrivateKey(store: KeyStore, keystoreAlias: String, password: CharArray): RSAPrivateCrtKey {
        val rsaKey = store.getKey(keystoreAlias, password)
        return rsaKey as RSAPrivateCrtKey
    }

    private fun generatePublicKey(rsaPrivateKey: RSAPrivateCrtKey): RSAPublicKey {
        val spec = RSAPublicKeySpec(rsaPrivateKey.modulus, rsaPrivateKey.publicExponent)
        return KeyFactory.getInstance(RSA_ALGORITHM).generatePublic(spec) as RSAPublicKey
    }

    private fun generateJwks(store: KeyStore, keystoreAlias: String, rsaPublicKey: RSAPublicKey): JWKSet {
        val keyID = getX509CertificateSerialNumber(store, keystoreAlias)

        val rsaKey = RSAKey.Builder(rsaPublicKey)
            .keyUse(KeyUse.SIGNATURE)
            .algorithm(JWSAlgorithm.RS256)
            .keyID(keyID).build()

        return JWKSet(rsaKey)
    }

    private fun getX509CertificateSerialNumber(store: KeyStore, keystoreAlias: String) =
        (store.getCertificate(keystoreAlias) as X509Certificate).serialNumber.toString()

    override fun getKeyPair(tokenType: TokenType) = jwksParametersMap[tokenType]!!.keyPair

    override fun getJwks(tokenType: TokenType) = jwksParametersMap[tokenType]!!.jwkSet

    override fun getKeyId(tokenType: TokenType) = jwksParametersMap[tokenType]!!.keyId

    companion object {

        private const val JKS_KEY_STORE_TYPE = "jks"
        private const val RSA_ALGORITHM = "RSA"
    }
}
