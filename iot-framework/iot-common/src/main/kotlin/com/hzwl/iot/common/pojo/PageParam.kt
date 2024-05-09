package com.hzwl.iot.common.pojo

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import java.io.Serializable

@Schema(description = "分页参数")
open class PageParam(

    @Schema(description = "页码, 从1开始", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @field:NotNull(message = "页码不能为空")
    @field:Min(value = 1, message = "页码不能小于 1")
    var page: Int? = 1,

    @Schema(description = "每页条数, 最大值为 100", example = "10", requiredMode = Schema.RequiredMode.REQUIRED)
    @field:NotNull(message = "每页条数不能为空")
    @field:Min(value = 1, message = "每页条数最小值为 1")
    @field:Max(value = 100, message = "每页条数最大值为 100")
    var size: Int? = 10
) : Serializable {
    companion object {
        const val PAGE_SIZE_NONE: Int = -1
    }
}
