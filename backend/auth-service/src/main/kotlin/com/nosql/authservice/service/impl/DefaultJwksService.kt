package com.nosql.authservice.service.impl

import com.nimbusds.jose.JWSAlgorithm
import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.KeyUse
import com.nimbusds.jose.jwk.RSAKey
import com.nosql.authservice.configuration.properties.JwtProperties
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

    private lateinit var internalKeyPair: KeyPair
    private lateinit var internalJwkSet: JWKSet
    private lateinit var internalKeyId: String

    init {
        val password = jwtProperties.keystore.password.toCharArray()
        val store = loadKeyStore(jwtProperties.keystore.resourcePath, password)
        val keystoreAlias = jwtProperties.keystore.keypairAlias

        internalKeyPair = generateKeyPair(store, keystoreAlias, password)
        internalJwkSet = generateJwks(store, keystoreAlias, internalKeyPair.public as RSAPublicKey)
        internalKeyId = getX509CertificateSerialNumber(store, keystoreAlias)
    }

    private fun generateKeyPair(store: KeyStore, keystoreAlias: String, password: CharArray): KeyPair {
        val rsaPrivateKey = generatePrivateKey(store, keystoreAlias, password)
        val rsaPublicKey = generatePublicKey(rsaPrivateKey)

        return KeyPair(rsaPublicKey, rsaPrivateKey)
    }

    private fun loadKeyStore(keystoreResourcePath: String, password: CharArray): KeyStore {
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

    override fun getKeyPair() = internalKeyPair

    override fun getJwks() = internalJwkSet

    override fun getKeyId() = internalKeyId

    companion object {

        private const val JKS_KEY_STORE_TYPE = "jks"
        private const val RSA_ALGORITHM = "RSA"
    }
}
