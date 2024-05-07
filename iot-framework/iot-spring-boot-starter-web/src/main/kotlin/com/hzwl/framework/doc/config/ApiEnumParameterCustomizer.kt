package com.hzwl.framework.doc.config

import com.hzwl.framework.common.enums.IEnum
import io.swagger.v3.oas.models.media.Schema
import io.swagger.v3.oas.models.parameters.Parameter
import org.springdoc.core.customizers.ParameterCustomizer
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component

/**
 * 枚举参数自定义配置类。用于为API枚举参数提供自定义配置，包括参数描述和枚举值的设置。
 */
@Component
class ApiEnumParameterCustomizer : ParameterCustomizer, IEnumCustomizer {

    /**
     * 自定义API参数配置。
     * @param parameterModel API参数模型，用于配置参数属性。
     * @param methodParameter 方法参数，表示当前被解析的方法参数信息。
     * @return 返回经过自定义配置后的参数模型。如果未进行任何修改，则返回原始参数模型。
     */
    override fun customize(parameterModel: Parameter?, methodParameter: MethodParameter): Parameter? {
        val parameterType = methodParameter.parameterType
        parameterModel?.let {
            // 检查当前参数类型是否为枚举类型
            if (IEnum::class.java.isAssignableFrom(parameterType)) {
                // 设置参数描述
                it.description = getDescription(parameterType)
                // 配置参数的枚举值
                val schema = Schema<Any>()
                schema.enum = getValues(parameterType)
                it.schema = schema
            }
        }
        return parameterModel
    }
}
