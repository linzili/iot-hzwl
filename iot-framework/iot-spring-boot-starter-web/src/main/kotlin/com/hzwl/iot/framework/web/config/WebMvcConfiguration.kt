package com.hzwl.iot.framework.web.config

import com.hzwl.iot.framework.jackson.core.databind.EnumConvertFactory
import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
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

    override fun extendMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        val converter = MappingJackson2HttpMessageConverter()
        converter.objectMapper = JacksonObjectMapper()
        converters.add(1, converter)
    }


}
