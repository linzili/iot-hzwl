package com.hzwl.iot.module.device.controller.product.vo.category

import com.hzwl.iot.module.device.dal.entity.product.ProductCategory
import io.github.linpeilie.annotations.AutoMapper
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size


@Schema(description = "设备管理 - 产品分类创建/修改 Request VO")
@AutoMapper(target = ProductCategory::class)
data class ProductCategorySaveReqVO(
    @Schema(description = "分类编号", example = "1024")
    val id: Long? = null,

    @Schema(description = "父分类编号", example = "1024")
    val parentId: Long?,

    @Schema(description = "分类名称", required = true, example = "办公")
    @field:NotBlank
    @field:Size(max = 100, message = "分类名称长度不能超过100个字符")
    val name: String?,

    @Schema(description = "排序", example = "1", required = true)
    @field:NotNull(message = "排序值不能为空")
    val sort: Int?,

    @Schema(description = "备注", example = "备注")
    val remark: String?,
)

