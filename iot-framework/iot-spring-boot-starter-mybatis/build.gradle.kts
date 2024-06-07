dependencies {
    implementation(project(":iot-framework:iot-common"))

    // mybatis-flex相关
    api("com.mybatis-flex:mybatis-flex-spring-boot3-starter")
    api("com.mybatis-flex:mybatis-flex-kotlin-extensions")
    runtimeOnly("com.mysql:mysql-connector-j")
    runtimeOnly("com.zaxxer:HikariCP")
}
