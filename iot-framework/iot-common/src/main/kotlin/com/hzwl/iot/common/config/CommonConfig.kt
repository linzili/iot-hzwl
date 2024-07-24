package com.hzwl.iot.common.config

import com.hzwl.iot.common.utils.IdWorker
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class CommonConfig {

    @Bean
     fun idWorker(): IdWorker = IdWorker()

}
