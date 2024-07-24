package com.hzwl.iot.module.system.controller.tenant.vo.packages

import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED

@Schema(description = "管理后台 - 租户套餐精简信息 Response VO")
data class TenantPackageSimpleRespVO(
    @Schema(description = "套餐编号", requiredMode = REQUIRED, example = "1024")
    val id: Long,

    @Schema(description = "租户套餐名称", requiredMode = REQUIRED, example = "翰臻云")
    val name: String
)
