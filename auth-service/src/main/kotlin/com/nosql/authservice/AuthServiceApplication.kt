package com.nosql.authservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
class AuthServiceApplication

fun main(args: Array<String>) {
	runApplication<AuthServiceApplication>(*args)
}
