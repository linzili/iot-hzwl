package com.hzwl.framework.doc.config

import com.fasterxml.jackson.databind.type.SimpleType
import com.hzwl.framework.common.enums.IEnum
import io.swagger.v3.core.converter.AnnotatedType
import io.swagger.v3.oas.models.media.Schema
import org.springdoc.core.customizers.PropertyCustomizer
import org.springframework.stereotype.Component
import org.springframework.util.ObjectUtils
import java.lang.reflect.ParameterizedType

/**
 * 枚举属性自定义配置类，用于定制Swagger文档中枚举类型的展示方式。
 */
@Component
class ApiEnumPropertyCustomizer : PropertyCustomizer, IEnumCustomizer {

    /**
     * 自定义Swagger字段的枚举展示。
     * @param property Swagger字段模型
     * @param type 字段的注解类型
     * @return 修改后的Swagger字段模型
     */
    override fun customize(property: Schema<*>, type: AnnotatedType): Schema<*> {
        // 检查类型是否为SimpleType并获取实际字段类
        if (type.type is SimpleType) {
            val fieldClazz = (type.type as SimpleType).rawClass

            // 检查字段类是否为枚举
            if (IEnum::class.java.isAssignableFrom(fieldClazz)) {
                // 获取枚举的父接口，用于解析枚举值的类型
                val genericInterfaces = fieldClazz.genericInterfaces
                if (genericInterfaces.isNotEmpty() && genericInterfaces[0] is ParameterizedType) {
                    // 解析枚举值的实际类型
                    val actualTypeArgument = (genericInterfaces[0] as ParameterizedType).actualTypeArguments[0]
                    val schema = getSchemaByType(actualTypeArgument, property)

                    // 设置枚举的值和描述
                    schema.enum = this.getValues(fieldClazz)
                    val description = this.getDescription(fieldClazz)
                    schema.description = if (ObjectUtils.isEmpty(property.description)) description else "${property.description} ($description)"

                    return schema
                }
            }
        }
        return property
    }
}
