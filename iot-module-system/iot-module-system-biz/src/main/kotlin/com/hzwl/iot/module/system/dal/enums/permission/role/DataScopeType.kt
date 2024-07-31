package com.hzwl.iot.module.system.dal.enums.permission.role

import com.fasterxml.jackson.annotation.JsonValue
import com.hzwl.iot.common.enums.IEnum
import com.mybatisflex.annotation.EnumValue

enum class DataScopeType(
    @JsonValue
    @EnumValue
    override val value: Int,
    val description: String
) : IEnum<Int> {

    ALL(1, "全部数据权限"),

    DEPT_CUSTOM(2, "指定部门数据权限"),

    DEPT_ONLY(3, "部门数据权限"),

    DEPT_AND_CHILD(4, "本部门及以下数据权限"),

    SELF(5, "仅本人数据权限"),

    ;

}
