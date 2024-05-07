package com.hzwl.framework.web.config

import com.hzwl.framework.jackson.core.databind.EnumConvertFactory
import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * WebMvc配置类，用于配置Spring MVC的一些行为。
 */
@Configuration
class WebMvcConfiguration : WebMvcConfigurer {
    /**
     * 向Spring MVC添加格式化器，用于支持枚举类型的转换。
     * @param registry 格式化器注册表，用于注册转换器。
     */
    override fun addFormatters(registry: FormatterRegistry) {
        // 注册EnumConvertFactory转换器，支持枚举类型的字符串与枚举实例之间的转换
        registry.addConverterFactory(EnumConvertFactory())
    }
}
