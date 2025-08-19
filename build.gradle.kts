plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	kotlin("plugin.jpa") version "1.9.25"
	id("org.springframework.boot") version "3.5.3"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.project"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
	// 로깅 충돌 해결을 위한 설정
	all {
		exclude(group = "commons-logging", module = "commons-logging")
		exclude(group = "org.slf4j", module = "slf4j-simple")
	}
}

repositories {
	mavenCentral()
}

val commonsLangVersion = "3.18.0"
val springdocVersion = "2.8.9"
val embeddedRedisVersion = "0.7.3"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	// Spring Boot Starter Security
	implementation("org.springframework.boot:spring-boot-starter-security")

	// Apache Commons Lang
	implementation("org.apache.commons:commons-lang3:$commonsLangVersion")

	// JPA
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	// Swagger
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springdocVersion")

	// Redis
	implementation("org.springframework.boot:spring-boot-starter-data-redis")

	// Embeded Redis - 로깅 충돌을 일으킬 수 있는 의존성 제외
	implementation("it.ozimov:embedded-redis:${embeddedRedisVersion}") {
		exclude(group = "org.slf4j", module = "slf4j-simple")
		exclude(group = "commons-logging", module = "commons-logging")
	}

	// Spring Security OAuth2 Resource Server
	implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")

	compileOnly("org.projectlombok:lombok")
	runtimeOnly("com.h2database:h2")
	// MariaDB JDBC 드라이버 추가
	runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
