package com.hzwl.iot.module.device.controller.device.vo

import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.module.device.dal.entity.device.Device
import io.github.linpeilie.annotations.AutoMapper
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size


@Schema(description = "设备管理 - 设备创建/修改 Request VO")
@AutoMapper(target = Device::class)
data class DeviceSaveReqVO(
    @Schema(description = "设备编号 有值使用传入的值，空值自动生成", example = "1024")
    val id: String?,

    @Schema(description = "设备名称", required = true, example = "雷达流量计")
    @field:NotBlank(message = "设备名称不能为空")
    @field:Size(max = 100, message = "设备名称长度不能超过100个字符")
    val name: String?,

    @Schema(description = "设备照片", example = "https://www.iocoder.cn/xx.png")
    val photoUrl: String?,

    @Schema(description = "产品编号", required = true, example = "1024")
    @field:NotNull(message = "产品编号不能为空")
    val productId: Long?,

    @Schema(description = "设备状态", required = true, example = "1")
    @field:NotNull(message = "设备状态不能为空")
    val status: CommonStatusEnum?,

    @Schema(description = "设备描述", example = "雷达流量计")
    val description: String?,

    @Schema(description = "备注", example = "备注")
    val remark: String?,
)
