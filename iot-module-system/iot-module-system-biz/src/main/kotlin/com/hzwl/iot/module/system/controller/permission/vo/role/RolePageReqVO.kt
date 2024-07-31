package com.hzwl.iot.module.system.controller.permission.vo.role

import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.common.pojo.PageParam
import com.hzwl.iot.common.utils.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

@Schema(description = "管理后台 - 角色分页 Request VO")
data class RolePageReqVO(

    @Schema(description = "角色名称,模糊匹配", example = "hzwl")
    var name: String? = null,

    @Schema(description = "角色标识，模糊匹配", example = "hzwl")
    var code: String? = null,

    @Schema(description = "角色状态（0正常 1停用）,参见 CommonStatusEnum 枚举类", example = "1")
    var status: CommonStatusEnum? = null,

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @Schema(description = "创建时间")
    val createTime: Array<LocalDateTime>?
) : PageParam()
