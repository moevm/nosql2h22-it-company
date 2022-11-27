package com.nosql.document

import io.mongock.runner.springboot.EnableMongock
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@EnableMongock
@ConfigurationPropertiesScan
@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
class DocumentServiceApplication

fun main(args: Array<String>) {
    runApplication<DocumentServiceApplication>(args = args)
}
