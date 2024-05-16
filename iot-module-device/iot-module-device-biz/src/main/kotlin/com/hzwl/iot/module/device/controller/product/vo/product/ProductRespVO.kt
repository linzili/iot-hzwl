package com.hzwl.iot.module.device.controller.product.vo.product

import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.module.device.dal.entity.product.Product
import com.hzwl.iot.module.device.enums.DeviceTypeEnum
import com.mybatisflex.annotation.Column
import com.mybatisflex.core.handler.JacksonTypeHandler
import io.github.linpeilie.annotations.AutoMapper
import io.github.linpeilie.annotations.ReverseAutoMapping
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "设备管理 - 产品详情 Response VO")
@AutoMapper(target = Product::class)
data class ProductRespVO(
    @Schema(description = "产品编号", example = "1", required = true)
    val id: Long,

    @Schema(description = "产品名称", example = "测试产品", required = true)
    val name: String,

    @Schema(description = "产品描述", example = "测试产品")
    val description: String? = null,

    @Schema(description = "设备类型", example = "1", required = true)
    val deviceType: DeviceTypeEnum,

    @Schema(description = "是否发布", example = "1", required = true)
    var publish: CommonStatusEnum,

    @Schema(description = "产品分类编号", example = "1")
    val categoryId: Long?,

    @Schema(description = "产品分类名称", example = "测试分类")
    val categoryName: String?,

    @Schema(description = "产品配置", example = "{\"key\":\"value\"}")
    @Column(typeHandler = JacksonTypeHandler::class)
    val configuration: Map<String, Any>?,

    @Schema(description = "备注", example = "备注")
    val remark: String?,

    @Schema(description = "创建时间", example = "2020-10-09 00:00:00", required = true)
    @ReverseAutoMapping(dateFormat = "yyyy-MM-dd HH:mm:ss")
    var createTime: String,
)
