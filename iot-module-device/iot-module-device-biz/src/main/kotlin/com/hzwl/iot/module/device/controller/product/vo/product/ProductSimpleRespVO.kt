package com.hzwl.iot.module.device.controller.product.vo.product

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "设备管理 - 产品精简信息 Response VO")
data class ProductSimpleRespVO(
    @Schema(description = "产品编号", example = "1", required = true)
    val id: Long,

    @Schema(description = "产品名称", example = "测试产品", required = true)
    val name: String,
)
