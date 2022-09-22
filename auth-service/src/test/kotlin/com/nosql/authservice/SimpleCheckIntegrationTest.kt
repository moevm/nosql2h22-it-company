package com.nosql.authservice

import com.nosql.authservice.common.integration.AbstractIntegrationTest
import com.nosql.authservice.factory.SimpleCheckTestDataFactory
import com.nosql.authservice.factory.SimpleCheckTestDataFactory.Companion.ID
import com.nosql.authservice.repository.SimpleCheckRepository
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired


class SimpleCheckIntegrationTest : AbstractIntegrationTest() {

    @Autowired
    lateinit var simpleCheckRepository: SimpleCheckRepository

    @Test
    fun simpleCheckSave() {
        val givenSimpleCheckEntity = factory.buildSimpleCheckEntity()

        val actualResult = runBlocking { simpleCheckRepository.save(givenSimpleCheckEntity).awaitSingle() }

        val expectedResult = factory.buildSimpleCheckEntity()

        assertThat(actualResult)
            .usingRecursiveComparison()
            .isEqualTo(expectedResult)
    }

    @Test
    fun simpleCheckUpdate() {

        val givenSimpleCheckEntity = factory.buildSimpleCheckEntity()
        val updatedSimpleCheckEntity = factory.buildUpdatedSimpleCheckEntity()

        val actualResult = runBlocking {
            simpleCheckRepository.save(givenSimpleCheckEntity).awaitSingle()
            simpleCheckRepository.save(updatedSimpleCheckEntity).awaitSingle()
        }

        val expectedResult = factory.buildUpdatedSimpleCheckEntity()

        assertThat(actualResult)
            .usingRecursiveComparison()
            .isEqualTo(expectedResult)
    }

    @Test
    fun simpleCheckDelete() {
        val givenSimpleCheckEntity = factory.buildSimpleCheckEntity()

        runBlocking {
            simpleCheckRepository.save(givenSimpleCheckEntity).awaitSingle()
            simpleCheckRepository.delete(givenSimpleCheckEntity).awaitSingleOrNull()
        }

        val actualResult = simpleCheckRepository.findById(ID)

        assertMonoIsEmpty(actualResult)
    }

	@AfterEach
	fun clearTables() = runBlocking<Unit> { simpleCheckRepository.deleteAll().awaitSingleOrNull() }

    companion object {
        private val factory = SimpleCheckTestDataFactory()
    }

}
