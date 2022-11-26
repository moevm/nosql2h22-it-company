package com.nosql.personservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
class PersonServiceApplication

fun main(args: Array<String>) {
	runApplication<PersonServiceApplication>(args = args)
}
