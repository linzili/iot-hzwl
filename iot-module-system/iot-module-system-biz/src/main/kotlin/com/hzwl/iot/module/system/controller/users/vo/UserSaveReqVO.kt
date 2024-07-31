package com.hzwl.iot.module.system.controller.users.vo

import cn.hutool.core.util.ObjectUtil
import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.common.enums.SexEnum
import com.hzwl.iot.common.validation.Mobile
import com.hzwl.iot.module.system.dal.entity.users.User
import io.github.linpeilie.annotations.AutoMapper
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED
import jakarta.validation.constraints.*
import net.minidev.json.annotate.JsonIgnore

@Schema(description = "管理后台 - 用户保存 Request VO")
@AutoMapper(target = User::class)
data class UserSaveReqVO(

    @Schema(description = "用户编号", example = "1024")
    val id: Long? = null,

    @Schema(description = "用户账号", requiredMode = REQUIRED, example = "hzwl")
    @field:NotBlank(message = "用户账号不能为空")
    @field:Pattern(regexp = "^[a-zA-Z0-9]{4,30}$", message = "用户账号由 数字、大小写字母组成，长度为 4-30 位")
    @field:Size(min = 4, max = 30, message = "用户账号长度为 4-30 位")
    val username: String?,

    @Schema(description = "密码", example = "hzwl123")
    @field:Size(min = 4, max = 16, message = "密码长度为 4-16 位")
    val password: String?,

    @Schema(description = "用户昵称", requiredMode = REQUIRED, example = "张三")
    @field:NotBlank(message = "用户昵称不能为空")
    @field:Size(max = 30, message = "用户昵称长度不能超过30个字符")
    var nickname: String?,

    @Schema(description = "部门编号", example = "1024")
    val deptId: Long?,

    @Schema(description = "邮箱", example = "hzwl@iotsys.com")
    @field:Email(message = "邮箱格式不正确")
    @field:Size(max = 50, message = "邮箱长度不能超过 50 个字符")
    val email: String?,

    @Schema(description = "手机号码", requiredMode = REQUIRED, example = "15601691300")
    @field:NotBlank(message = "手机号码不能为空")
    @field:Mobile
    var phone: String?,

    @Schema(description = "用户状态（0正常 1停用）", requiredMode = REQUIRED, example = "1")
    @field:NotNull(message = "用户状态不能为空")
    val status: CommonStatusEnum?,

    @Schema(description = "用户性别（0男 1女 2未知）", example = "1")
    val sex: SexEnum?,

    @Schema(description = "头像", example = "hzwl.png")
    val avatar: String?,

    @Schema(description = "备注", example = "快乐的备注")
    val remark: String?

) {

    @AssertTrue(message = "密码不能为空")
    @JsonIgnore
    fun isPasswordValid(): Boolean = id != null || ObjectUtil.isAllNotEmpty(password)
}
