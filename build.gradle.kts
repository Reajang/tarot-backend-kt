import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

object DepVersions {
    const val KOTLIN = "1.8.22"
    const val KOTLIN_COROUTINES = "1.7.2"
    const val JAVA = "19"

    const val SPRING_BOOT = "3.1.1"
    const val SPRING = "6.1.1"
    const val POSTGRES = "42.6.0"

    const val MAPSTRUCT = "1.5.5.Final"
    const val LOMBOK = "1.18.28"
    const val FLYWAY = "9.16.2"
    const val KAFKA_SPRING = "3.0.8"
    const val KAFKA_CLIENTS = "3.5.0"

    const val TESTCONTAINERS = "1.18.3"
    const val MOCKITO_JUNIT_JUPITER = "5.3.1"

    const val APACHE_COMMON_COLLECTIONS_4 = "4.0"
    const val APACHE_COMMON_LANG_3 = "3.3"
}

plugins {
//    plugins versions
    val kotlinVersion = "1.8.22"
    val flywayVersion = "9.16.2"
    id("org.springframework.boot") version "3.1.1"
//    id "io.spring.dependency-management" version "1.1.0"
    id("org.flywaydb.flyway") version flywayVersion
//    id("java")
//    id "maven-publish"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
//    application
}

group = "com.tarot"
version = "1.0-SNAPSHOT"
//sourceCompatibility = "19"

//apply plugin: "maven-publish"

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(DepVersions.JAVA))
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(DepVersions.JAVA))
    }
}

repositories {
    mavenCentral()
}

//configurations {
//    compileOnly {
//        extendsFrom annotationProcessor
//    }
//}


dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${DepVersions.KOTLIN_COROUTINES}")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:${DepVersions.SPRING_BOOT}")
    implementation("org.springframework.boot:spring-boot-starter-data-rest:${DepVersions.SPRING_BOOT}")
    implementation("org.springframework.boot:spring-boot-starter-aop:${DepVersions.SPRING_BOOT}")
//    implementation("org.springframework.boot:spring-boot-starter-security:${DepVersions.SPRING_BOOT}")
    implementation("org.springframework.boot:spring-boot-starter-web:${DepVersions.SPRING_BOOT}")
    implementation("org.flywaydb:flyway-core:${DepVersions.FLYWAY}")
    implementation("org.mapstruct:mapstruct:${DepVersions.MAPSTRUCT}")

    implementation("org.springframework.kafka:spring-kafka:${DepVersions.KAFKA_SPRING}")
    implementation("org.apache.kafka:kafka-clients:${DepVersions.KAFKA_CLIENTS}")

    implementation("org.apache.commons:commons-collections4:${DepVersions.APACHE_COMMON_COLLECTIONS_4}")
    implementation("org.apache.commons:commons-lang3:${DepVersions.APACHE_COMMON_LANG_3}")

    compileOnly("org.projectlombok:lombok:${DepVersions.LOMBOK}")
    compileOnly ("org.springframework.boot:spring-boot-devtools:${DepVersions.SPRING_BOOT}")

    runtimeOnly("org.postgresql:postgresql:${DepVersions.POSTGRES}")

    annotationProcessor("org.projectlombok:lombok:${DepVersions.LOMBOK}")
    annotationProcessor("org.mapstruct:mapstruct-processor:${DepVersions.MAPSTRUCT}")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:${DepVersions.SPRING_BOOT}")

    testImplementation(kotlin("test"))
    testImplementation("org.springframework.boot:spring-boot-starter-test:${DepVersions.SPRING_BOOT}")
//    testImplementation("org.springframework.security:spring-security-test:${DepVersions.SPRING}")
    testImplementation("org.testcontainers:testcontainers:${DepVersions.TESTCONTAINERS}")
    testImplementation("org.testcontainers:junit-jupiter:${DepVersions.TESTCONTAINERS}")
    testImplementation("org.testcontainers:kafka:${DepVersions.TESTCONTAINERS}")
    testImplementation("org.testcontainers:postgresql:${DepVersions.TESTCONTAINERS}")
    testImplementation("org.springframework.kafka:spring-kafka-test:${DepVersions.KAFKA_SPRING}")
    testImplementation("org.mockito:mockito-junit-jupiter:${DepVersions.MOCKITO_JUNIT_JUPITER}")
//    }
//        exclude group: "org.junit.vintage", module: "junit-vintage-engine"
//    testImplementation("org.springframework.boot:spring-boot-starter-test") {

}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = DepVersions.JAVA
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
    }
}

//application {
//    mainClass.set("MainKt")
//}