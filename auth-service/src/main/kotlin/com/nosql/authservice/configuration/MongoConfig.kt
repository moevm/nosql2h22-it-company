package com.nosql.authservice.configuration

import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@Configuration
@EnableReactiveMongoRepositories(basePackageClasses = [MongoConfig::class])
class MongoConfig : AbstractReactiveMongoConfiguration() {

    @Value("\${spring.data.mongodb.uri}")
    private lateinit var mongoUri: String

    fun mongoClient(): MongoClient {
        return MongoClients.create(mongoUri)
    }

    override fun getDatabaseName(): String {
        return "it-service-db"
    }
}