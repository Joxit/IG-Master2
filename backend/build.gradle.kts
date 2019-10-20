import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

buildscript {
  extra.apply {
    set("springVersion", "5.2.0.RELEASE")
    set("mysqlVersion", "8.0.17")
  }
}

plugins {
  java
  maven
  id("idea")
  id("com.github.ben-manes.versions") version "0.26.0"
}

fun isNonStable(version: String): Boolean {
  val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
  val regex = "^[0-9,\\.v\\-]+(-r)?$".toRegex()
  val isStable = stableKeyword || regex.matches(version)
  return isStable.not()
}

tasks.dependencyUpdates {
  rejectVersionIf {
    isNonStable(candidate.version)
  }
}

allprojects {
  apply(plugin = "maven")
  apply(plugin = "idea")

  group = "io.github.joxit"
  version = "1.0-SNAPSHOT"
}

subprojects {
  apply(plugin = "java")

  java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }

  repositories {
    mavenLocal()
    mavenCentral()

    maven { url = uri("https://repo.maven.apache.org/maven2") }
  }
}