dependencies {
    api(project(":iot-framework:iot-spring-boot-starter-security"))
    implementation(project(":iot-framework:iot-spring-boot-starter-mybatis"))
    implementation("org.springframework.boot:spring-boot-starter-test")
    implementation(project(":iot-module-device:iot-module-device-api"))
    ksp("com.mybatis-flex:mybatis-flex-kotlin-ksp:1.0.1")
}
