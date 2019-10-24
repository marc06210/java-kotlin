package com.afklm.tecc.kotlin

import org.springframework.boot.runApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class KotlinApp

fun main(args: Array<String>) {
    runApplication<KotlinApp>(*args)
}