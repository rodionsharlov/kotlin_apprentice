import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
}

plugins {
    kotlin("jvm") version "1.5.31"
    application
}

group = "me.rodion"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}
