package com.hzwl.iot.module.device.controller.product.vo.product

import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.common.pojo.PageParam
import com.hzwl.iot.common.utils.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND
import com.hzwl.iot.module.device.enums.DeviceTypeEnum
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

@Schema(description = "设备管理 - 产品分页 Request VO ")
data class ProductPageReqVO(

    @Schema(description = "产品名称，模糊匹配", example = "测试产品")
    val name: String?,

    @Schema(description = "设备类型", example = "1")
    val deviceType: DeviceTypeEnum?,

    @Schema(description = "是否发布", example = "1")
    var publish: CommonStatusEnum?,

    @Schema(description = "产品分类编号", example = "1024")
    val categoryId: Long?,

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @Schema(description = "创建时间")
    val createTime: Array<LocalDateTime>?
) : PageParam()
