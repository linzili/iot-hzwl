package com.hzwl.iot.module.system.controller.tenant.vo.tenant

import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.common.pojo.PageParam
import com.hzwl.iot.common.utils.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

@Schema(description = "管理后台 - 租户分页 Request VO")
data class TenantPageReqVO(

    @Schema(description = "租户名,模糊匹配", example = "翰臻云")
    val name: String? = null,

    @Schema(description = "联系人,模糊匹配", example = "张三")
    val contactName: String? = null,

    @Schema(description = "租户状态（0正常 1停用）", example = "1")
    val status: CommonStatusEnum? = null,

    @Schema(description = "域名",  example = "hanzhenyun.com")
    val website: String? = null,

    @Schema(description = "套餐编号", example = "1024")
    val packageId: Long? = null,

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @Schema(description = "创建时间")
    val createTime: Array<LocalDateTime>?
) : PageParam()
