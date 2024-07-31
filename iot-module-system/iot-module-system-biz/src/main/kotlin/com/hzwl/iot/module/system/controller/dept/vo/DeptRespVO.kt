package com.hzwl.iot.module.system.controller.dept.vo

import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.module.system.dal.entity.dept.Dept
import com.mybatisflex.annotation.RelationOneToMany
import io.github.linpeilie.annotations.AutoMapper
import io.github.linpeilie.annotations.ReverseAutoMapping
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED

@Schema(description = "管理后台 - 部门分页 Response VO")
@AutoMapper(target = Dept::class)
data class DeptRespVO(

    @Schema(description = "部门编号", requiredMode = REQUIRED, example = "1024")
    val id: Long,

    @Schema(description = "部门名称", requiredMode = REQUIRED, example = "超级管理员")
    val name: String,

    @Schema(description = "父部门 ID", requiredMode = REQUIRED, example = "1")
    val parentId: Long,

    @Schema(description = "部门排序", requiredMode = REQUIRED, example = "1")
    val sort: Int,

    @Schema(description = "负责人的用户编号", example = "1")
    val leaderUserId: Long?,

    @Schema(description = "联系电话", example = "超级管理员")
    val phone: String?,

    @Schema(description = "邮箱", example = "超级管理员")
    val email: String?,

    @Schema(description = "部门状态（0正常 1停用）", requiredMode = REQUIRED, example = "1")
    val status: CommonStatusEnum,

    @Schema(description = "子部门")
    @RelationOneToMany(selfField = "id", targetField = "parentId", targetTable = "system_dept")
    var children: List<DeptRespVO>? = null,

    @Schema(description = "创建时间", requiredMode = REQUIRED, example = "2020-10-09 00:00:00")
    @ReverseAutoMapping(dateFormat = "yyyy-MM-dd HH:mm:ss")
    var createTime: String
)
