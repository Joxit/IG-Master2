package io.github.joxit.pizzeria.vertx

import io.netty.util.ResourceLeakDetector
import io.vertx.core.DeploymentOptions
import io.vertx.core.Vertx
import kotlin.system.exitProcess
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@PropertySource(
  value = ["classpath:application.properties", "application.properties", "/conf/application.properties"],
  ignoreResourceNotFound = true
)
@EnableJpaRepositories(basePackages = ["io.github.joxit.pizzeria.persistence"])
@ComponentScan(
  "io.github.joxit.pizzeria.persistence",
  "io.github.joxit.pizzeria.service",
  "io.github.joxit.pizzeria.mapper",
  "io.github.joxit.pizzeria.vertx"
)
@EnableTransactionManagement
@Configuration
class VertexExample(
  vertx: Vertx,
  verticleFactory: SpringVerticleFactory
) {
  companion object {
    private val LOGGER = LoggerFactory.getLogger(VertexExample::class.java)

    @JvmStatic
    fun main(args: Array<String>) {
      AnnotationConfigApplicationContext(VertexExample::class.java)
    }
  }

  init {
    val start = System.currentTimeMillis()
    ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.DISABLED)
    vertx.registerVerticleFactory(verticleFactory)

    // Scale the verticles on cores
    val cores = Runtime.getRuntime().availableProcessors()
    val options = DeploymentOptions().setInstances(cores)

    vertx.deployVerticle(verticleFactory.prefix() + ":" + HttpServerVerticle::class.java.name, options)
      .onComplete {
        if (it.succeeded()) {
          LOGGER.info("Pizzeria app started in {} ms", System.currentTimeMillis() - start)
        } else {
          LOGGER.error("Error in server initialization", it.cause())
          exitProcess(1)
        }
      }
  }
}