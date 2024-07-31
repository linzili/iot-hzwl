package com.hzwl.iot.module.system.controller.users.vo

import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.common.enums.SexEnum
import com.hzwl.iot.module.system.dal.entity.users.User
import io.github.linpeilie.annotations.AutoMapper
import io.github.linpeilie.annotations.ReverseAutoMapping
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED
import java.time.LocalDateTime

@Schema(description = "管理后台 - 用户分页 Response VO")
@AutoMapper(target = User::class)
data class UserRespVO(

    @Schema(description = "用户编号", requiredMode = REQUIRED, example = "1024")
    val id: Long,

    @Schema(description = "用户账号", requiredMode = REQUIRED, example = "hzwl")
    val username: String,

    @Schema(description = "用户昵称", requiredMode = REQUIRED, example = "张三")
    val nickname: String,

    @Schema(description = "邮箱", example = "hzwl@iotsys.com")
    val email: String?,

    @Schema(description = "手机号码", requiredMode = REQUIRED, example = "15601691300")
    val phone: String,

    @Schema(description = "部门编号", example = "1024")
    val deptId: Long?,

    @Schema(description = "用户状态（0正常 1停用）", requiredMode = REQUIRED, example = "1")
    val status: CommonStatusEnum,

    @Schema(description = "用户性别（0男 1女 2未知）", requiredMode = REQUIRED, example = "1")
    val sex: SexEnum,

    @Schema(description = "头像", example = "hzwl.png")
    val avatar: String?,

    @Schema(description = "备注", example = "快乐的备注")
    val remark: String?,

    @Schema(description = "最后登录IP", example = "127.0.0.1")
    val loginIp: String?,

    @Schema(description = "最后登录时间", example = "2020-10-09 00:00:00")
    val loginDate: LocalDateTime?,

    @Schema(description = "创建时间", requiredMode = REQUIRED, example = "2020-10-09 00:00:00")
    @ReverseAutoMapping(dateFormat = "yyyy-MM-dd HH:mm:ss")
    var createTime: String
)
