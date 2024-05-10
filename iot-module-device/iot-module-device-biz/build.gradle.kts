dependencies {
    api(project(":iot-framework:iot-spring-boot-starter-web"))
    implementation(project(":iot-framework:iot-spring-boot-starter-mybatis"))
    implementation("org.springframework.boot:spring-boot-starter-test")
    implementation(project(":iot-module-device:iot-module-device-api"))
}
