package com.hzwl.iot.module.system.controller.tenant.vo.tenant

import cn.hutool.core.util.ObjectUtil
import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.common.validation.Mobile
import com.hzwl.iot.module.system.controller.users.vo.UserSaveReqVO
import com.hzwl.iot.module.system.dal.entity.tenant.Tenant
import io.github.linpeilie.annotations.AutoMapper
import io.github.linpeilie.annotations.AutoMappers
import io.github.linpeilie.annotations.AutoMapping
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED
import jakarta.validation.constraints.*
import net.minidev.json.annotate.JsonIgnore
import java.io.Serializable
import java.time.LocalDateTime

@Schema(description = "管理后台 - 租户创建/修改 Request VO")
@AutoMappers(
    AutoMapper(target = Tenant::class),
    AutoMapper(target = UserSaveReqVO::class, reverseConvertGenerate = false)
)
data class TenantSaveReqVO(

    @Schema(description = "租户编号", example = "1024")
    val id: Long? = null,

    @Schema(description = "租户名称", requiredMode = REQUIRED, example = "翰臻云")
    @field:NotBlank(message = "租户名称不能为空")
    val name: String?,

    @Schema(description = "用户账号", example = "hzwl")
    @field:Pattern(regexp = "^[a-zA-Z0-9]{4,30}$", message = "用户账号由 数字、大小写字母组成，长度为 4-30 位")
    @field:Size(min = 4, max = 30, message = "用户账号长度为 4-30 位")
    val username: String?,

    @Schema(description = "密码", example = "hzwl123")
    @field:Size(min = 4, max = 16, message = "密码长度为 4-16 位")
    val password: String?,

    @Schema(description = "联系人名称", requiredMode = REQUIRED, example = "张三")
    @field:NotBlank(message = "联系人名称不能为空")
    @AutoMapping(targetClass = UserSaveReqVO::class, target = "nickname")
    val contactName: String?,

    @Schema(description = "联系电话", requiredMode = REQUIRED, example = "15601691300")
    @field:NotBlank(message = "联系电话不能为空")
    @field:Mobile
    @AutoMapping(targetClass = UserSaveReqVO::class, target = "phone")
    val contactPhone: String?,

    @Schema(description = "状态", requiredMode = REQUIRED, example = "0")
    @field:NotNull(message = "状态不能为空")
    var status: CommonStatusEnum?,

    @Schema(description = "域名", requiredMode = REQUIRED, example = "hanzhenyun.com")
    @field:NotBlank(message = "域名不能为空")
    val website: String?,

    @Schema(description = "套餐编号", requiredMode = REQUIRED, example = "1024")
    @field:NotNull(message = "套餐编号不能为空")
    val packageId: Long?,

    @Schema(description = "过期时间", requiredMode = REQUIRED, example = "2022-02-02 00:00:00")
    @field:NotNull(message = "过期时间不能为空")
    val expireTime: LocalDateTime?,

    @Schema(description = "账号数量", requiredMode = REQUIRED, example = "1024")
    @field:NotNull(message = "账号数量不能为空")
    @field:Min(value = 1, message = "账号数量必须大于 0")
    val accountCount: Int?
) : Serializable {
    @AssertTrue(message = "用户账号不能为空")
    @JsonIgnore
    fun isUsernameValid(): Boolean = id != null || ObjectUtil.isAllNotEmpty(username)

    @AssertTrue(message = "密码不能为空")
    @JsonIgnore
    fun isPasswordValid(): Boolean = id != null || ObjectUtil.isAllNotEmpty(password)
}
