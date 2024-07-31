package com.hzwl.iot.module.system.controller.permission.vo.role

import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.module.system.dal.entity.permission.Role
import com.hzwl.iot.module.system.dal.enums.permission.role.DataScopeType
import com.hzwl.iot.module.system.dal.enums.permission.role.RoleType
import io.github.linpeilie.annotations.AutoMapper
import io.github.linpeilie.annotations.ReverseAutoMapping
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED

@Schema(description = "管理后台 - 角色分页 Response VO")
@AutoMapper(target = Role::class)
data class RoleRespVO(

    @Schema(description = "角色编号", requiredMode = REQUIRED, example = "1024")
    val id: Long,

    @Schema(description = "角色名称", requiredMode = REQUIRED, example = "超级管理员")
    val name: String,

    @Schema(description = "角色标识", requiredMode = REQUIRED, example = "admin")
    val code: String,

    @Schema(description = "角色排序", requiredMode = REQUIRED, example = "1")
    val sort: Int,

    @Schema(description = "角色状态（0正常 1停用）", requiredMode = REQUIRED, example = "1")
    val status: CommonStatusEnum,

    @Schema(description = "角色类型", requiredMode = REQUIRED)
    val type: RoleType,

    @Schema(description = "数据范围", requiredMode = REQUIRED, example = "1")
    val dataScope: DataScopeType,

    @Schema(description = "数据范围(指定部门数组)")
    val dataScopeDeptIds: Set<Long>?,

    @Schema(description = "备注", example = "我是备注")
    val remark: String?,


    @Schema(description = "创建时间", requiredMode = REQUIRED, example = "2020-10-09 00:00:00")
    @ReverseAutoMapping(dateFormat = "yyyy-MM-dd HH:mm:ss")
    var createTime: String
)
