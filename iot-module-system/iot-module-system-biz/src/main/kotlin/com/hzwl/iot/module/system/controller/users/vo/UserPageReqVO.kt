package com.hzwl.iot.module.system.controller.users.vo

import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.common.pojo.PageParam
import com.hzwl.iot.common.utils.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

@Schema(description = "管理后台 - 用户分页 Request VO")
data class UserPageReqVO(
    @Schema(description = "用户账号,模糊匹配", example = "hzwl")
    var username: String? = null,

    @Schema(description = "手机号码,模糊匹配", example = "13800138000")
    var phone: String? = null,

    @Schema(description = "用户状态（0正常 1停用）,参见 CommonStatusEnum 枚举类", example = "1")
    var status: CommonStatusEnum? = null,

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @Schema(description = "创建时间")
    val createTime: Array<LocalDateTime>?
) : PageParam()
