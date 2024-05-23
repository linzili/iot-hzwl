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


include("iot-framework:iot-common")
findProject(":iot-framework:iot-common")?.name = "iot-common"

include("iot-framework:iot-spring-boot-starter-security")
findProject(":iot-framework:iot-spring-boot-starter-security")?.name = "iot-spring-boot-starter-security"

include("iot-framework:iot-spring-boot-starter-mybatis")
findProject(":iot-framework:iot-spring-boot-starter-mybatis")?.name = "iot-spring-boot-starter-mybatis"

include("iot-framework:iot-spring-boot-starter-web")
findProject(":iot-framework:iot-spring-boot-starter-web")?.name = "iot-spring-boot-starter-web"

include("iot-framework:iot-spring-boot-starter-websocket")
findProject(":iot-framework:iot-spring-boot-starter-websocket")?.name = "iot-spring-boot-starter-websocket"

include("iot-framework:iot-tools")
findProject(":iot-framework:iot-tools")?.name = "iot-tools"


include("iot-framework:iot-tools:iot-tools-network-debugger")
findProject(":iot-framework:iot-tools:iot-tools-network-debugger")?.name = "iot-tools-network-debugger"

include("iot-module-system")
include("iot-module-system:iot-module-system-biz")
findProject(":iot-module-system:iot-module-system-biz")?.name = "iot-module-system-biz"
include("iot-module-system:iot-module-system-api")
findProject(":iot-module-system:iot-module-system-api")?.name = "iot-module-system-api"

include("iot-module-device")
include("iot-module-device:iot-module-device-api")
findProject(":iot-module-device:iot-module-device-api")?.name = "iot-module-device-api"
include("iot-module-device:iot-module-device-biz")
findProject(":iot-module-device:iot-module-device-biz")?.name = "iot-module-device-biz"


include("iot-module-tools")
include("iot-module-tools:iot-module-tools-api")
findProject(":iot-module-tools:iot-module-tools-api")?.name = "iot-module-tools-api"
include("iot-module-tools:iot-module-tools-biz")
findProject(":iot-module-tools:iot-module-tools-biz")?.name = "iot-module-tools-biz"
