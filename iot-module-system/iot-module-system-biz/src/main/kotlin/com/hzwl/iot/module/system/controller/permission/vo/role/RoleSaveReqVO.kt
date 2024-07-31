package com.hzwl.iot.module.system.controller.permission.vo.role

import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.module.system.dal.entity.permission.Role
import io.github.linpeilie.annotations.AutoMapper
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@Schema(description = "管理后台 - 角色保存 Request VO")
@AutoMapper(target = Role::class)
data class RoleSaveReqVO(


    @Schema(description = "角色编号", example = "1024")
    val id: Long?,

    @Schema(description = "角色名称", requiredMode = REQUIRED, example = "超级管理员")
    @field:NotBlank(message = "角色名称不能为空")
    @field:Size(max = 30, message = "角色名称长度不能超过30个字符")
    val name: String?,

    @Schema(description = "角色标识", requiredMode = REQUIRED, example = "admin")
    @field:NotBlank(message = "角色标识不能为空")
    @field:Size(max = 100, message = "角色标识长度不能超过100个字符")
    val code: String?,

    @Schema(description = "角色排序", requiredMode = REQUIRED, example = "1")
    @field:NotNull(message = "角色排序不能为空")
    val sort: Int?,

    @Schema(description = "角色状态（0正常 1停用）", example = "1")
    val status: CommonStatusEnum?,

    @Schema(description = "备注", example = "快乐的备注")
    val remark: String?

)
