package com.schedulo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@EnableMongoRepositories
@SpringBootApplication
class ScheduloApplication

fun main(args: Array<String>) {
	runApplication<ScheduloApplication>(*args)
}
