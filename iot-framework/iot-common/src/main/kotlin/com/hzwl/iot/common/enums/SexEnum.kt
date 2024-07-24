package com.hzwl.iot.common.enums

import com.fasterxml.jackson.annotation.JsonValue
import com.mybatisflex.annotation.EnumValue

/**
 * 性别枚举
 *
 * @author 林子立
 * @date 2023/5/31 10:06
 * @since 1.0.0
 */
enum class SexEnum(
    @JsonValue
    @EnumValue
    override val value: Int,
    val description: String,
) : IEnum<Int> {
    MAN(0, "男"),
    WOMEN(1, "女"),
    UNKNOWN(2, "未知"),
    ;

    companion object {
        fun isMan(status: Int) = MAN.value == status
        fun isWomen(status: Int) = WOMEN.value == status
        fun isUnknown(status: Int) = UNKNOWN.value == status
    }
}
