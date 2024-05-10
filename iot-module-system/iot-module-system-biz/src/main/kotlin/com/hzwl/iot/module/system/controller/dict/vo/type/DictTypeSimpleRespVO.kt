package com.hzwl.iot.module.system.controller.dict.vo.type

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "管理后台 - 字典类型精简信息 Response VO")
data class DictTypeSimpleRespVO(

    @Schema(description = "字典类型编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    val id: Long,

    @Schema(description = "字典类型名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "iot")
    val name: String,

    @Schema(description = "字典类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "sys_common_sex")
    val type: String,
)
