package com.hzwl.iot.module.system.controller.tenant.vo.packages

import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.module.system.dal.entity.tenant.TenantPackage
import io.github.linpeilie.annotations.AutoMapper
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.io.Serializable

@Schema(description = "管理后台 - 租户套餐保存 Request VO")
@AutoMapper(target = TenantPackage::class)
data class TenantPackageSaveReqVO(

    @Schema(description = "租户套餐编号", example = "1024")
    val id: Long? = null,

    @Schema(description = "租户套餐名称", requiredMode = REQUIRED, example = "高级套餐")
    @field:NotBlank(message = "租户套餐名称不能为空")
    val name: String?,

    @Schema(description = "状态", requiredMode = REQUIRED, example = "0")
    @field:NotNull(message = "状态不能为空")
    var status: CommonStatusEnum?,

    @Schema(description = "备注", example = "好")
    val remark: String?,

    @Schema(description = "菜单编号列表", requiredMode = REQUIRED, example = "1024")
//    @field:NotNull(message = "菜单编号列表不能为空")
    val menuIds: List<Long>? = null

) : Serializable
