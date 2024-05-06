rootProject.name = "iot-hzwl"

pluginManagement {

    repositories {
        mavenLocal()
        maven {
            url = uri("https://maven.aliyun.com/repository/public/")
        }
        gradlePluginPortal()
        mavenCentral()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}



include("iot-server")
include("iot-framework")
include("iot-module-system")

include("iot-framework:iot-common")
findProject(":iot-framework:iot-common")?.name = "iot-common"

include("iot-framework:iot-spring-boot-starter-mybatis")
findProject(":iot-framework:iot-spring-boot-starter-mybatis")?.name = "iot-spring-boot-starter-mybatis"
include("iot-framework:iot-spring-boot-starter-web")
findProject(":iot-framework:iot-spring-boot-starter-web")?.name = "iot-spring-boot-starter-web"
include("iot-module-system:iot-module-system-biz")
findProject(":iot-module-system:iot-module-system-biz")?.name = "iot-module-system-biz"
include("iot-module-system:iot-module-system-api")
findProject(":iot-module-system:iot-module-system-api")?.name = "iot-module-system-api"
include("iot-framework:iot-spring-boot-starter-security")
findProject(":iot-framework:iot-spring-boot-starter-security")?.name = "iot-spring-boot-starter-security"
