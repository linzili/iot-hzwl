package com.hzwl.iot.module.system.controller.tenant.vo.tenant

import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.module.system.dal.entity.tenant.Tenant
import io.github.linpeilie.annotations.AutoMapper
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.io.Serializable
import java.time.LocalDateTime

@Schema(description = "管理后台 - 租户创建/修改 Request VO")
@AutoMapper(target = Tenant::class)
data class TenantSaveReqVO(

    @Schema(description = "租户编号", example = "1024")
    val id: Long? = null,

    @Schema(description = "租户名称", requiredMode = REQUIRED, example = "翰臻云")
    @field:NotBlank(message = "租户名称不能为空")
    val name: String?,

    @Schema(description = "联系人用户编号", requiredMode = REQUIRED, example = "1")
    @field:NotNull(message = "联系人用户编号不能为空")
    val contactUserId: Long?,

    @Schema(description = "联系人名称", requiredMode = REQUIRED, example = "张三")
    @field:NotBlank(message = "联系人名称不能为空")
    val contactName: String?,

    @Schema(description = "联系电话", requiredMode = REQUIRED, example = "15601691300")
    @field:NotBlank(message = "联系电话不能为空")
    val contactPhone: String?,

    @Schema(description = "状态", requiredMode = REQUIRED, example = "0")
    @field:NotNull(message = "状态不能为空")
    var status: CommonStatusEnum?,

    @Schema(description = "域名", requiredMode = REQUIRED, example = "hanzhenyun.com")
    @field:NotBlank(message = "域名不能为空")
    val website: String?,

    @Schema(description = "套餐编号", requiredMode = REQUIRED, example = "1024")
    @field:NotNull(message = "套餐编号不能为空")
    val packageId: Long?,

    @Schema(description = "过期时间", requiredMode = REQUIRED, example = "2022-02-02 00:00:00")
    @field:NotNull(message = "过期时间不能为空")
    val expireTime: LocalDateTime?,

    @Schema(description = "账号数量", requiredMode = REQUIRED, example = "1024")
    @field:NotNull(message = "账号数量不能为空")
    @field:Min(value = 1, message = "账号数量必须大于 0")
    val accountCount: Int?
) : Serializable
