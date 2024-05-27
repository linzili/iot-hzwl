package com.hzwl.iot.module.tools.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "iot.tools.network-debugger")
 class ToolsProperties{
    lateinit var ip:String
 }
