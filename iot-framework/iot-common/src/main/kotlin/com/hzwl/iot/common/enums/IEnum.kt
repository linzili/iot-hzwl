package com.hzwl.iot.common.enums

import com.fasterxml.jackson.annotation.JsonValue
import java.io.Serializable

/**
 * 通用枚举接口，定义了枚举的基本行为和属性。
 *
 * @param <V> 枚举值的类型，该类型需实现Serializable接口，以便于序列化和反序列化。
 * @param <E> 子枚举类型，指明实现该接口的具体枚举类。
 */
interface IEnum<V : Serializable> {

    /**
     * 获取枚举项的值。该属性被[@JsonValue]注解标记，用于在JSON序列化和反序列化时使用。
     */
    @get:JsonValue
    val value: V

    /**
     * 从值反序列化枚举。这是一个companion object方法，用于根据提供的值查找并返回对应的枚举常量。
     *
     * @param value 要查找的枚举值。
     * @param B 实现IEnum接口的枚举类。
     * @return 找到的枚举常量，如果未找到则返回null。
     */
    companion object {
        inline fun <reified B, V> Class<B>.fromValue(value: V): B?
                where B : IEnum<V>, V : Serializable {
            return enumConstants?.asSequence()
                ?.filter { it.value == value } // 筛选出值匹配的枚举常量
                ?.firstOrNull() // 获取第一个匹配项，若无则返回null
        }
    }
}
