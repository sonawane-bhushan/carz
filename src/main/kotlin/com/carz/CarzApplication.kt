package com.carz

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.carz.*")
class CarzApplication

fun main(args: Array<String>) {
	runApplication<CarzApplication>(*args)
}
