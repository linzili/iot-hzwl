package com.hzwl.iot.module.system.controller.dict.vo.data

import com.hzwl.framework.common.enums.CommonStatusEnum
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.io.Serializable

@Schema(description = "管理后台 - 字段数据创建/修改 Request VO")
data class DictDataSaveReqVo(

    @Schema(description = "字典数据编号", example = "1024")
    var id: Long,

    @Schema(description = "排序值", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @field:NotNull(message = "排序值不能为空")
    var sort: Long?,

    @field:NotBlank(message = "字典类型不能为空")
    var label: String?,

    var value: String,

    var dictType: String,

    var status: CommonStatusEnum,

    var colorType: String? = null,

    var cssClass: String? = null,

    var remark: String? = null,
) : Serializable
