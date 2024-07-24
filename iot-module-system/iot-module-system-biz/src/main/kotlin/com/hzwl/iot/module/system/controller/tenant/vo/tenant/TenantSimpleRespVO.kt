package com.hzwl.iot.module.system.controller.tenant.vo.tenant

import com.hzwl.iot.module.system.dal.entity.tenant.Tenant
import io.github.linpeilie.annotations.AutoMapper
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "管理后台 - 租户精简信息 Response VO")
@AutoMapper(target = Tenant::class)
data class TenantSimpleRespVO(
    @Schema(description = "租户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    val id: Long,

    @Schema(description = "租户名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "翰臻云")
    val name: String
)
