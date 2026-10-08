plugins {
  java
  id("war")
}

tasks.war {
  enabled = true
  archiveFileName.set("pizzeria-servlet.war")
}

dependencies {
  implementation(project(":pizzeria-core"))
  implementation("org.springframework:spring-web")
  implementation("org.springframework:spring-webmvc")
  implementation("org.springframework:spring-webflux")

  implementation("jakarta.ws.rs:jakarta.ws.rs-api:${property("version.jakarta")}")
  implementation("jakarta.servlet.jsp.jstl:jakarta.servlet.jsp.jstl-api:${property("version.jstl")}")
  implementation("org.glassfish.web:jakarta.servlet.jsp.jstl:${property("version.jstl")}")
  implementation("tools.jackson.core:jackson-databind:${property("version.jackson")}")
  compileOnly("jakarta.servlet:jakarta.servlet-api:${property("version.servlet")}")
  compileOnly("org.apache.tomcat.embed:tomcat-embed-core:${property("version.tomcat")}")
}
