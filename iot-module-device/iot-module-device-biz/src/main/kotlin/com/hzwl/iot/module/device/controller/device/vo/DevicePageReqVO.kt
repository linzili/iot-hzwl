package com.hzwl.iot.module.device.controller.device.vo

import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.common.pojo.PageParam
import com.hzwl.iot.common.utils.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

@Schema(description = "设备管理 - 设备分页 Request VO")
data class DevicePageReqVO(

    @Schema(description = "设备名称，模糊匹配", example = "河东雷达流量计")
    val name: String?,

    @Schema(description = "产品编号", example = "1024")
    val productId: Long?,

    @Schema(description = "设备状态", example = "1")
    val status: CommonStatusEnum?,

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @Schema(description = "创建时间")
    val createTime: Array<LocalDateTime>?
) : PageParam()
