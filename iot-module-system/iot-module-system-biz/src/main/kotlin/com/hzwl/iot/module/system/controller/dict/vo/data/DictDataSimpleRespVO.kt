package com.hzwl.iot.module.system.controller.dict.vo.data

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "管理后台 - 字典数据精简 Response VO")
data class DictDataSimpleRespVO(

    @Schema(description = "字典类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "sys_common_sex")
    val dictType: String,

    @Schema(description = "字典标签", requiredMode = Schema.RequiredMode.REQUIRED, example = "iot")
    val label: String,

    @Schema(description = "字典值", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    val value: String,

    @Schema(description = "颜色类型, default、primary、success、warning、danger、info", example = "default")
    val colorType: String? = null,

    @Schema(description = "CSS 样式", example = "btn-visible")
    val cssClass: String? = null,
)
