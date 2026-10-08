package io.github.joxit.pizzeria.vertx

import com.jolbox.bonecp.BoneCPDataSource
import io.vertx.core.Vertx
import java.util.Properties
import javax.sql.DataSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class Configuration(
  @param:Value("\${dataSource.username}") private val dataSourceUsername: String,
  @param:Value("\${dataSource.jdbcUrl}") private val jdbcUrl: String,
  @param:Value("\${dataSource.password}") private val dataSourcePassword: String
) {
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
}