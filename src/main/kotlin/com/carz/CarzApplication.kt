package com.carz

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CarzApplication

fun main(args: Array<String>) {
	runApplication<CarzApplication>(*args)
}
