package com.hzwl.iot.common.enums

import com.fasterxml.jackson.annotation.JsonValue
import com.mybatisflex.annotation.EnumValue

/**
 * 通用状态枚举
 *
 * @author lin
 */
enum class CommonStatusEnum(
    @JsonValue
    @EnumValue
    override val value: Int,
    val description: String,
) : IEnum<Int> {
    ENABLE(0, "启用"),
    DISABLE(1, "禁用"),
    ;

    companion object {
        fun isEnable(status: Int) = ENABLE.value == status

        fun isDisable(status: Int) = DISABLE.value == status
    }
}
