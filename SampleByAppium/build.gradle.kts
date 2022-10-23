import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

val appiumClientVersion = "8.1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    // JUnit 5
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")

    // Appium
    testImplementation("io.appium:java-client:$appiumClientVersion")

    // Assert J
    testImplementation("org.assertj:assertj-core:3.23.1")

    // https://mvnrepository.com/artifact/org.slf4j/slf4j-simple
    testImplementation("org.slf4j:slf4j-simple:2.0.3")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}