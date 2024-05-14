package com.hzwl.iot.module.device.controller.product.vo.product

import com.hzwl.iot.module.device.enums.DeviceTypeEnum
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@Schema(description = "产品创建/修改 Request VO")
data class ProductSaveReqVO(
    @Schema(description = "产品编号", example = "1")
    val id: Long? = null,

    @Schema(description = "产品名称", required = true, example = "测试产品")
    @field:NotBlank(message = "产品名称不能为空")
    val name: String?,

    @Schema(description = "产品描述", example = "测试产品")
    val description: String? = null,

    @Schema(description = "设备类型", required = true, example = "1")
    @field:NotNull(message = "设备类型不能为空")
    val deviceType: DeviceTypeEnum?,

    @Schema(description = "产品分类编号", example = "1")
    val categoryId: Long?,

    @Schema(description = "备注", example = "备注")
    val remark: String? = null,
)
