package com.hzwl.iot.module.system.controller.dept.vo

import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.common.validation.Mobile
import com.hzwl.iot.module.system.dal.entity.dept.Dept
import io.github.linpeilie.annotations.AutoMapper
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@Schema(description = "管理后台 - 部门保存 Request VO")
@AutoMapper(target = Dept::class)
data class DeptSaveReqVO(

    @Schema(description = "部门编号", example = "1024")
    val id: Long?,

    @Schema(description = "部门名称", requiredMode = REQUIRED, example = "超级管理员")
    @field:NotBlank(message = "部门名称不能为空")
    @field:Size(max = 30, message = "部门名称长度不能超过30个字符")
    val name: String?,

    @Schema(description = "父部门编号", example = "1024")
    val parentId: Long?,

    @Schema(description = "部门排序", requiredMode = REQUIRED, example = "1")
    @field:NotNull(message = "部门排序不能为空")
    val sort: Int?,

    @Schema(description = "负责人的用户编号", example = "1")
    val leaderUserId: Long?,

    @Schema(description = "联系电话", example = "13954984112")
    @field:Mobile
    val phone: String?,

    @Schema(description = "邮箱", example = "hzwl@iotsys.com")
    @field:Email(message = "邮箱格式不正确")
    val email: String?,

    @Schema(description = "部门状态（0正常 1停用）", requiredMode = REQUIRED,example = "1")
    @field:NotNull(message = "部门状态不能为空")
    val status: CommonStatusEnum?
)
