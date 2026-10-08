package io.github.joxit.pizzeria

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableWebMvc
@SpringBootApplication
class SpringBootExample

fun main(args: Array<String>) {
  SpringApplication.run(SpringBootExample::class.java, *args)
}