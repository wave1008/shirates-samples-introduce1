import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

val shiratesCoreVersion = "1.1.0"
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

    // shirates-core
    testImplementation("io.github.ldi-github:shirates-core:$shiratesCoreVersion")

    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core
    testImplementation("org.apache.logging.log4j:log4j-core:2.18.0")

    // https://mvnrepository.com/artifact/org.slf4j/slf4j-nop
    testImplementation("org.slf4j:slf4j-nop:1.7.36")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}