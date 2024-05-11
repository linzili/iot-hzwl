plugins {
    id("application")
}
dependencies {
    implementation(project(":iot-module-system:iot-module-system-biz"))
    implementation(project(":iot-module-device:iot-module-device-biz"))
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-test")
}

application {
    mainClass = "com.hzwl.iot.ServerApplicationKt"
}
