package io.github.joxit.pizzeria

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.reactive.config.EnableWebFlux

@EnableWebFlux
@SpringBootApplication
class WebfluxExample

fun main(args: Array<String>) {
  SpringApplication.run(WebfluxExample::class.java, *args)
}