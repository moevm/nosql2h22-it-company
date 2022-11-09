package com.nosql.authservice.common.integration

import com.nosql.authservice.entity.SimpleCheckEntity
import org.junit.jupiter.api.BeforeAll
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

@Testcontainers
@DataMongoTest(excludeAutoConfiguration = [EmbeddedMongoAutoConfiguration::class])
abstract class AbstractIntegrationTest {

    @Throws(AssertionError::class)
    protected fun assertMonoIsEmpty(mono: Mono<out SimpleCheckEntity>) =
        StepVerifier.create(mono).expectComplete().verify()

    companion object {
        @Container
        private var mongoDB = MongoDBContainer("mongo:5.0.12")

        val ignoringFields = arrayOf("id")

        @JvmStatic
        @BeforeAll
        fun startTestContainers() {
            mongoDB.start()
        }

        @JvmStatic
        @DynamicPropertySource
        fun registerPgProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.data.mongodb.uri") { "mongodb://mongo:mongo@localhost:27017/it-service-db?authSource=admin" }
        }
    }
}