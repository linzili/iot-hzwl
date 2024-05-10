package com.hzwl.iot.module.system.controller.dict.vo.type

import com.hzwl.iot.common.enums.CommonStatusEnum
import io.swagger.v3.oas.annotations.media.Schema


@Schema(description = "管理后台 - 字典类型 Response VO")
data class DictTypeRespVO(
    @Schema(description = "字典类型编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    val id: Long,

    @Schema(description = "字典名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "iot")
    val name: String,

    @Schema(description = "字典类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "sys_common_sex")
    val type: String,

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    val status: CommonStatusEnum,

    @Schema(description = "备注", example = "备注")
    val remark: String,

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    var createTime: String,
)
