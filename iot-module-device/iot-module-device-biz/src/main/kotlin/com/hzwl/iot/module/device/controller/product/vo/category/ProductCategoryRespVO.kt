package com.hzwl.iot.module.device.controller.product.vo.category

import com.hzwl.iot.module.device.dal.entity.product.ProductCategory
import io.github.linpeilie.annotations.AutoMapper
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull


@Schema(description = "设备管理 - 产品分类响应 Request VO")
@AutoMapper(target = ProductCategory::class)
data class ProductCategoryRespVO(
    @Schema(description = "分类编号", example = "1024", required = true)
    val id: Long? = null,

    @Schema(description = "父分类编号", example = "1024")
    val parentId: Long?,

    @Schema(description = "分类名称", required = true, example = "办公")
    val name: String?,

    @Schema(description = "排序", required = true, example = "1")
    @field:NotNull(message = "排序值不能为空")
    val sort: Int?,

    @Schema(description = "备注", example = "备注")
    val remark: String?,
)

