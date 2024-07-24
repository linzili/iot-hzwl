dependencies {
    api("cn.dev33:sa-token-spring-boot3-starter")
    api("org.springframework.security:spring-security-crypto")

    api(project(":iot-framework:iot-spring-boot-starter-web"))

}
description = """
     1. security：用户的认证、权限的校验，实现「谁」可以做「什么事」
     2. operatelog：操作日志，实现「谁」在「什么时间」对「什么」做了「什么事」
""".trimIndent()
