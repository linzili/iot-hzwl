dependencies {
    api(project(":iot-framework:iot-spring-boot-starter-websocket"))
    implementation(project(":iot-framework:iot-tools:iot-tools-network-debugger"))
    implementation("org.springframework.boot:spring-boot-starter-test")
    implementation(project(":iot-module-tools:iot-module-tools-api"))
}
