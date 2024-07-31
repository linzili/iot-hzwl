package com.hzwl.iot.module.system.controller.users.vo

import com.hzwl.iot.module.system.dal.entity.users.User
import io.github.linpeilie.annotations.AutoMapper
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED

@Schema(description = "管理后台 - 用户精简信息 Response VO")
@AutoMapper(target = User::class)
data class UserSimpleRespVO(
    @Schema(description = "用户编号", requiredMode = REQUIRED, example = "1024")
    val id: Long,

    @Schema(description = "用户昵称", requiredMode = REQUIRED, example = "张三")
    val nickname: String
)
