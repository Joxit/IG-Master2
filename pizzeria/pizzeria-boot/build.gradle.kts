plugins {
  java
  kotlin("jvm")
  id("org.springframework.boot")
}

tasks.bootJar {
  archiveFileName.set("pizzeria-boot.jar")
  manifest.attributes["Implementation-Version"] = project.version
}

dependencies {
  implementation(project(":pizzeria-core"))
  implementation(kotlin("stdlib"))
  implementation(kotlin("reflect"))
  implementation("org.springframework.boot:spring-boot")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-configuration-processor")
  implementation("org.springframework.boot:spring-boot-starter-jdbc")
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}
