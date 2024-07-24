package com.hzwl.iot.module.system.controller.tenant.vo.tenant

import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.module.system.dal.entity.tenant.Tenant
import io.github.linpeilie.annotations.AutoMapper
import io.github.linpeilie.annotations.ReverseAutoMapping
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED
import java.time.LocalDateTime

@Schema(description = "管理后台 - 租户分页 Response VO")
@AutoMapper(target = Tenant::class)
data class TenantRespVO(
    @Schema(description = "租户编号", requiredMode = REQUIRED, example = "1024")
    val id: Long,

    @Schema(description = "租户名称", requiredMode = REQUIRED, example = "翰臻云")
    val name: String,

    @Schema(description = "联系人用户编号", requiredMode = REQUIRED, example = "1")
    val contactUserId: Long,

    @Schema(description = "联系人名称", requiredMode = REQUIRED, example = "张三")
    val contactName: String,

    @Schema(description = "联系电话", requiredMode = REQUIRED, example = "15601691300")
    val contactPhone: String,

    @Schema(description = "状态", requiredMode = REQUIRED, example = "0")
    var status: CommonStatusEnum,

    @Schema(description = "域名", requiredMode = REQUIRED, example = "hanzhenyun.com")
    val website: String,

    @Schema(description = "套餐编号", requiredMode = REQUIRED, example = "1024")
    val packageId: Long,

    @Schema(description = "过期时间", requiredMode = REQUIRED, example = "2022-02-02 00:00:00")
    val expireTime: LocalDateTime,

    @Schema(description = "账号数量", requiredMode = REQUIRED, example = "1024")
    val accountCount: Int,

    @Schema(description = "创建时间", requiredMode = REQUIRED, example = "2020-10-09 00:00:00")
    @ReverseAutoMapping(dateFormat = "yyyy-MM-dd HH:mm:ss")
    var createTime: String,
)
