package com.nosql.personservice

import io.mongock.runner.springboot.EnableMongock
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@EnableMongock
@ConfigurationPropertiesScan
@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
class PersonServiceApplication

fun main(args: Array<String>) {
	runApplication<PersonServiceApplication>(args = args)
}
