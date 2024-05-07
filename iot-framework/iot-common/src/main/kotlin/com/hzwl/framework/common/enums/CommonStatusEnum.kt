package com.hzwl.framework.common.enums

import com.fasterxml.jackson.annotation.JsonValue
/**
 * 通用状态枚举
 *
 * @author lin
 */
enum class CommonStatusEnum(
    @JsonValue
    override val value: Int,
    val label: String,
) : IEnum<Int> {
    ENABLE(0, "启用"),
    DISABLE(1, "禁用"),
    ;

    companion object {
        fun isEnable(status: Int) = ENABLE.value == status

        fun isDisable(status: Int) = DISABLE.value == status
    }
}
