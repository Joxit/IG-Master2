package io.github.joxit.osm

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@EnableWebMvc
@SpringBootApplication
class OSMApplication

fun main(args: Array<String>) {
  runApplication<OSMApplication>(*args)
}
