val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val koin_version: String by project

plugins {
    application
    kotlin("jvm") version "1.5.21"
    kotlin("plugin.serialization") version "1.5.21"
}

group = "com.sample"
version = "0.0.1"
application {
    mainClass.set("com.sample.ApplicationKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-serialization:$ktor_version")
    implementation("io.insert-koin:koin-ktor:$koin_version")

    testImplementation("io.insert-koin:koin-test:$koin_version")
    testImplementation("io.insert-koin:koin-test-junit5:$koin_version")
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    testImplementation("org.assertj:assertj-core:3.20.2")
    testImplementation("org.mockito.kotlin:mockito-kotlin:3.2.0")

}
tasks.test {
    useJUnitPlatform()
}