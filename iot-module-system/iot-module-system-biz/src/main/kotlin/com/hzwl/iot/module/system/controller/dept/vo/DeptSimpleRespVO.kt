package com.hzwl.iot.module.system.controller.dept.vo

import com.hzwl.iot.module.system.dal.entity.dept.Dept
import com.mybatisflex.annotation.RelationOneToMany
import io.github.linpeilie.annotations.AutoMapper
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED

@Schema(description = "管理后台 - 部门精简信息 Response VO")
@AutoMapper(target = Dept::class)
data class DeptSimpleRespVO(
    @Schema(description = "部门编号", requiredMode = REQUIRED, example = "1024")
    val id: Long,

    @Schema(description = "部门名称", requiredMode = REQUIRED, example = "管理员")
    val name: String,

    @Schema(description = "父部门编号", requiredMode = REQUIRED, example = "1024")
    val parentId: Long,

    @Schema(description = "子部门")
    @RelationOneToMany(selfField = "id", targetField = "parentId", targetTable = "system_dept")
    var children: List<DeptSimpleRespVO>? = null,
)
