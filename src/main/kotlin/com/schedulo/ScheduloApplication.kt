package com.schedulo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ScheduloApplication

fun main(args: Array<String>) {
	runApplication<ScheduloApplication>(*args)
}
