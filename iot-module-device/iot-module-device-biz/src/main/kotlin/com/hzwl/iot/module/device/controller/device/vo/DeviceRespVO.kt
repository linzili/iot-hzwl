package com.hzwl.iot.module.device.controller.device.vo

import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.module.device.dal.entity.device.Device
import io.github.linpeilie.annotations.AutoMapper
import io.github.linpeilie.annotations.ReverseAutoMapping
import io.swagger.v3.oas.annotations.media.Schema


@Schema(description = "设备管理 - 设备 Response VO")
@AutoMapper(target = Device::class)
data class DeviceRespVO(
    @Schema(description = "设备编号", required = true, example = "1024")
    val id: String,

    @Schema(description = "设备名称", required = true, example = "河东雷达流量计")
    val name: String,

    @Schema(description = "设备照片", example = "https://www.iocoder.cn/xx.png")
    val photoUrl: String?,

    @Schema(description = "产品编号", required = true, example = "1024")
    val productId: Long,

    @Schema(description = "产品名称", required = true, example = "雷达流量计")
    val productName: String,

    @Schema(description = "设备状态", required = true, example = "1")
    val status: CommonStatusEnum,

    @Schema(description = "设备描述", example = "雷达流量计")
    val description: String?,

    @Schema(description = "备注", example = "备注")
    val remark: String?,

    @Schema(description = "创建时间", required = true)
    @ReverseAutoMapping(dateFormat = "yyyy-MM-dd HH:mm:ss")
    val createTime: String,
)
