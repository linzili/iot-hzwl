package com.hzwl.iot.framework.jackson.core.databind

import com.hzwl.iot.common.enums.IEnum
import org.springframework.core.convert.converter.Converter
import org.springframework.core.convert.converter.ConverterFactory

/**
 * 枚举转换工厂类，用于将字符串转换为指定的枚举类型。
 */
class EnumConvertFactory : ConverterFactory<String, IEnum<*>> {

    /**
     * 获取一个转换器，用于将字符串转换为指定的枚举类型T。
     *
     * @param targetType 转换目标枚举类型的Class对象。
     * @return 返回一个字符串到枚举类型的转换器。
     */
    override fun <T : IEnum<*>> getConverter(targetType: Class<T>): Converter<String, T> {
        return Converter { source ->
            // 尝试找到与给定字符串匹配的枚举常量，如果没有找到则返回null。
            targetType.enumConstants.firstOrNull { it.value.toString() == source }
        }
    }
}
