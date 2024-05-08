import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.spring") version "1.9.23"
    kotlin("kapt") version "1.9.23"
    kotlin("plugin.noarg") version "1.9.23"
}

group = "com.hzwl"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenLocal()
    maven { url = uri("https://maven.aliyun.com/repository/public/") }
    mavenCentral()
}



ext {
    set("mybatis-flex.version", "1.8.8")
    set("mybatis-flex-kotlin.version", "1.0.8")
    set("mapstruct-plus.version", "1.4.0")
    set("springdoc.version", "2.5.0")
    set("hutool.version", "5.8.27")
    set("transmittable-thread-local.version", "2.14.5")
    set("sa-token.version", "1.37.0")
}
subprojects {
    apply {
        plugin("java")
        plugin("java-library")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.plugin.spring")
        plugin("org.jetbrains.kotlin.plugin.noarg")
        plugin("org.jetbrains.kotlin.kapt")
    }
    group = project.group
    version = project.version

    noArg {
        annotation("com.mybatisflex.annotation.Table")
        invokeInitializers = true
    }

    configurations.all() {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
    }
    configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    repositories {
        mavenLocal()
        maven { url = uri("https://maven.aliyun.com/repository/public/") }
        mavenCentral()
    }

    tasks {
        withType(JavaCompile::class) { options.encoding = "UTF-8" }
        withType(KotlinCompile::class) {
            kotlinOptions {
                freeCompilerArgs += "-Xjsr305=strict"
                jvmTarget = "21"
            }
        }
        test { useJUnitPlatform() }
    }


    dependencyManagement {
        dependencies {
            dependency("io.github.linpeilie:mapstruct-plus-spring-boot-starter:${rootProject.ext["mapstruct-plus.version"]}")
            dependency("io.github.linpeilie:mapstruct-plus-processor:${rootProject.ext["mapstruct-plus.version"]}")
            dependency("com.mybatis-flex:mybatis-flex-spring-boot3-starter:${rootProject.ext["mybatis-flex.version"]}")
            dependency("com.mybatis-flex:mybatis-flex-core:${rootProject.ext["mybatis-flex.version"]}")
            dependency("com.mybatis-flex:mybatis-flex-processor:${rootProject.ext["mybatis-flex.version"]}")
            dependency("com.mybatis-flex:mybatis-flex-kotlin-extensions:${rootProject.ext["mybatis-flex-kotlin.version"]}")
            dependency("org.springdoc:springdoc-openapi-starter-webmvc-ui:${rootProject.ext["springdoc.version"]}")
            dependency("cn.hutool:hutool-all:${rootProject.ext["hutool.version"]}")
            dependency("com.alibaba:transmittable-thread-local:${rootProject.ext["transmittable-thread-local.version"]}")
            dependency("cn.dev33:sa-token-spring-boot-starter:${rootProject.ext["sa-token.version"]}")

        }
    }
    dependencies {
        kapt("com.mybatis-flex:mybatis-flex-processor")
        kapt("io.github.linpeilie:mapstruct-plus-processor")
    }
}

