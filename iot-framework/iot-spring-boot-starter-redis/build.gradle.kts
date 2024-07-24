dependencies {
    implementation(project(":iot-framework:iot-common"))
    api("org.springframework.boot:spring-boot-starter-data-redis")
    api("org.springframework.boot:spring-boot-starter-cache")
    api("org.apache.commons:commons-pool2")
}
