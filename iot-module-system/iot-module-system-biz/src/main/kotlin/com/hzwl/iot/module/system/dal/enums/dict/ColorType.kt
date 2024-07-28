package com.hzwl.iot.module.system.dal.enums.dict

import com.fasterxml.jackson.annotation.JsonValue
import com.hzwl.iot.common.enums.IEnum
import com.mybatisflex.annotation.EnumValue

enum class ColorType(
    @JsonValue
    @EnumValue
    override val value: Int,
    val description: String
) : IEnum<Int> {
    DEFAULT(1, "默认"),
    PRIMARY(2, "主要"),
    SUCCESS(3, "成功"),
    WARNING(4, "警告"),
    DANGER(5, "危险"),
    ;

}
