package com.spartakt.coffee_order

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class CoffeeOrderApplication

fun main(args: Array<String>) {
    runApplication<CoffeeOrderApplication>(*args)
}
