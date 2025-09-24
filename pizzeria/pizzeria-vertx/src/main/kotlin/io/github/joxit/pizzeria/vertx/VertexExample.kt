package io.github.joxit.pizzeria.vertx

import com.jolbox.bonecp.BoneCPDataSource
import io.netty.util.ResourceLeakDetector
import io.vertx.core.DeploymentOptions
import io.vertx.core.Vertx
import java.util.Properties
import javax.annotation.PostConstruct
import javax.sql.DataSource
import kotlin.system.exitProcess
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
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
class VertexExample {
  @Value("\${dataSource.username}")
  private lateinit var dataSourceUsername: String

  @Value("\${dataSource.jdbcUrl}")
  private lateinit var jdbcUrl: String

  @Value("\${dataSource.password}")
  private lateinit var dataSourcePassword: String

  @Autowired
  private lateinit var vertx: Vertx

  @Autowired
  private lateinit var verticleFactory: SpringVerticleFactory

  companion object {
    private val LOGGER = LoggerFactory.getLogger(VertexExample::class.java)

    @JvmStatic
    fun main(args: Array<String>) {
      AnnotationConfigApplicationContext(VertexExample::class.java)
    }
  }

  @Bean
  fun dataSource(): DataSource {
    val dataSource = BoneCPDataSource()
    dataSource.driverClass = "com.mysql.cj.jdbc.Driver"
    dataSource.jdbcUrl = "jdbc:$jdbcUrl"
    dataSource.username = dataSourceUsername
    dataSource.password = dataSourcePassword
    return dataSource
  }

  @Bean
  fun jdbcTemplate(@Autowired dataSource: DataSource): JdbcTemplate {
    return JdbcTemplate(dataSource)
  }

  @Bean
  fun entityManagerFactory(@Autowired dataSource: DataSource): LocalContainerEntityManagerFactoryBean {
    val factory = LocalContainerEntityManagerFactoryBean()
    factory.dataSource = dataSource
    val vendorAdapter = HibernateJpaVendorAdapter()
    factory.dataSource = dataSource
    factory.jpaVendorAdapter = vendorAdapter
    factory.setPackagesToScan("io.github.joxit.pizzeria.model")
    val jpaProperties = Properties()
    jpaProperties["hibernate.dialect"] = "org.hibernate.dialect.MySQLDialect"
    factory.setJpaProperties(jpaProperties)
    return factory
  }

  @Bean
  fun transactionManager(@Autowired entityManagerFactory: LocalContainerEntityManagerFactoryBean): PlatformTransactionManager {
    return JpaTransactionManager(entityManagerFactory.nativeEntityManagerFactory)
  }

  @Bean
  fun vertx(): Vertx = Vertx.vertx()

  @PostConstruct
  private fun deployVerticle() {
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