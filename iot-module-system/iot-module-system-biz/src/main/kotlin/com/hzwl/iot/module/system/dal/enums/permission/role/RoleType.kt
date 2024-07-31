package com.hzwl.iot.module.system.dal.enums.permission.role

import com.fasterxml.jackson.annotation.JsonValue
import com.hzwl.iot.common.enums.IEnum
import com.mybatisflex.annotation.EnumValue

enum class RoleType(
    @JsonValue
    @EnumValue
    override val value: Int,
    val description: String
) : IEnum<Int> {
    SYSTEM(1, "系统角色"),
    CUSTOM(2, "自定义角色");
}
