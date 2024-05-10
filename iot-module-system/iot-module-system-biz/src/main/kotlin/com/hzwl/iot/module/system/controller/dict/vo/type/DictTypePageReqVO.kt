package com.hzwl.iot.module.system.controller.dict.vo.type

import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.common.pojo.PageParam
import com.hzwl.iot.common.utils.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Size
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

@Schema(description = "管理后台 - 字典类型分页 Request VO")
data class DictTypePageReqVO(
    @Schema(description = "字典类型名称,模糊匹配", example = "性别")
    val name: String? = null,

    @field:Size(max = 100, message = "字典类型类型长度不能超过100个字符")
    @Schema(description = "字典类型,模糊匹配", example = "sys_common_sex")
    val type: String? = null,

    @Schema(description = "状态,参见 CommonStatusEnum 枚举类", example = "1")
    val status: CommonStatusEnum? = null,

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @Schema(description = "创建时间")
    val createTime: Array<LocalDateTime>?

) : PageParam()
