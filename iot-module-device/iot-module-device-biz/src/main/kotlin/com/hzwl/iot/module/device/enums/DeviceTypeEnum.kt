package com.hzwl.iot.module.device.enums

import com.fasterxml.jackson.annotation.JsonValue
import com.hzwl.iot.common.enums.IEnum
import com.mybatisflex.annotation.EnumValue

enum class DeviceTypeEnum(
    @JsonValue
    @EnumValue
    override val value: Int,
    val description: String,
) : IEnum<Int> {
    DIRECT(1, "直连设备"),
    GATEWAY(2, "网关设备"),
    SUB_DEVICE(3, "网关子设备")
    ;
}
