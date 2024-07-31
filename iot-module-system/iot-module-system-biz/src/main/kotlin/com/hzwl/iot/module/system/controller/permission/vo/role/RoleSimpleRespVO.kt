package com.hzwl.iot.module.system.controller.permission.vo.role

import com.hzwl.iot.module.system.dal.entity.permission.Role
import io.github.linpeilie.annotations.AutoMapper
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED

@Schema(description = "管理后台 - 角色精简信息 Response VO")
@AutoMapper(target = Role::class)
data class RoleSimpleRespVO(
    @Schema(description = "角色编号", requiredMode = REQUIRED, example = "1024")
    val id: Long,

    @Schema(description = "角色名称", requiredMode = REQUIRED, example = "管理员")
    val name: String
)
