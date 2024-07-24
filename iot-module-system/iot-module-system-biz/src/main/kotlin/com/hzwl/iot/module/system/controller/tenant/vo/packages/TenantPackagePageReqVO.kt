package com.hzwl.iot.module.system.controller.tenant.vo.packages

import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.common.pojo.PageParam
import com.hzwl.iot.common.utils.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

@Schema(description = "管理后台 - 租户套餐分页 Request VO")
data class TenantPackagePageReqVO(

    @Schema(description = "套餐名,模糊匹配", example = "VIP")
    val name: String? = null,

    @Schema(description = "状态,参见 CommonStatusEnum 枚举类", example = "1")
    val status: CommonStatusEnum? = null,

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @Schema(description = "创建时间")
    val createTime: Array<LocalDateTime>?

) : PageParam()
