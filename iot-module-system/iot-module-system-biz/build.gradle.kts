dependencies {
    api(project(":iot-framework:iot-spring-boot-starter-security"))
    implementation(project(":iot-framework:iot-spring-boot-starter-mybatis"))
    implementation(project(":iot-framework:iot-spring-boot-starter-redis"))
    implementation("org.springframework.boot:spring-boot-starter-test")
    implementation(project(":iot-module-system:iot-module-system-api"))
    ksp("com.mybatis-flex:mybatis-flex-kotlin-ksp:1.0.1")
}
