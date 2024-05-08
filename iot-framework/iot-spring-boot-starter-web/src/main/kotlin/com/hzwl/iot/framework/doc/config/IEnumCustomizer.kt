package com.hzwl.iot.framework.doc.config

import io.swagger.v3.core.util.PrimitiveType
import io.swagger.v3.oas.models.media.ObjectSchema
import io.swagger.v3.oas.models.media.Schema
import org.springframework.beans.BeanUtils
import org.springframework.util.ReflectionUtils
import java.lang.reflect.Modifier
import java.lang.reflect.Type

/**
 * IEnum枚举自定义器接口，提供操作枚举的自定义方法
 * @see IEnum
 */
interface IEnumCustomizer {

    /**
     * 获取枚举的所有值
     *
     * @param enumClazz 枚举的class
     * @return 枚举的所有值列表
     */
    fun getValues(enumClazz: Class<*>): List<Any> {
        // 通过反射获取枚举类的所有常量
        return enumClazz.enumConstants
            .mapNotNull { item ->
                // 收集每个枚举值的"getValue"方法返回的值
                val getValue = ReflectionUtils.findMethod(item.javaClass, "getValue")
                getValue?.let {
                    ReflectionUtils.makeAccessible(it)
                    ReflectionUtils.invokeMethod(it, item)
                }
            }
    }

    /**
     * 获取枚举值和对应描述的字符串表示
     *
     * @param enumClazz 枚举的class
     * @return 描述信息字符串
     */
    fun getDescription(enumClazz: Class<*>): String {
        // 获取枚举类中非静态字段，并按名称降序排序
        val fieldList = enumClazz.declaredFields
            .filter { !Modifier.isStatic(it.modifiers) }
            .sortedByDescending { it.name }

        // 使所有字段可访问
        fieldList.forEach { ReflectionUtils.makeAccessible(it) }

        // 构建每个枚举值及其字段描述的字符串
        return enumClazz.enumConstants
            .filterNotNull().joinToString("； ") { item ->
                fieldList.joinToString(" : ") { field ->
                    field.get(item).toString()
                }
            }
    }

    /**
     * 根据枚举值的类型获取相应的Swagger Schema对象
     *
     * @param type         枚举值的类型
     * @param sourceSchema 从属性中加载的原始Schema对象
     * @return 枚举值类型对应的Schema对象
     */
    fun getSchemaByType(type: Type, sourceSchema: Schema<*>): Schema<Any> {
        // 根据枚举值类型决定使用哪种类型的Schema
        val schema = PrimitiveType.fromType(type)?.createProperty()
            ?: ObjectSchema()

        // 复制原始schema的属性到新schema
        BeanUtils.copyProperties(sourceSchema, schema)

        // 返回配置好的schema
        return schema
    }
}
