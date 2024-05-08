package com.hzwl.iot.module.system.controller.dict.vo.data

import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.module.system.dal.entity.dict.DictData
import io.github.linpeilie.annotations.AutoMapper
import io.github.linpeilie.annotations.ReverseAutoMapping
import io.swagger.v3.oas.annotations.media.Schema
import java.io.Serializable

@Schema(description = "管理后台 - 字段数据信息 Response VO")
@AutoMapper(target = DictData::class)
data class DictDataRespVO(

    @Schema(description = "字典数据编号", example = "1024")
    val id: Long,

    @Schema(description = "排序值", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    val sort: Long,

    @Schema(description = "字典标签", requiredMode = Schema.RequiredMode.REQUIRED, example = "iot")
    val label: String,

    @Schema(description = "字典值", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    val value: String,

    @Schema(description = "字典类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "sys_common_sex")
    val dictType: String,

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    val status: CommonStatusEnum,

    @Schema(description = "颜色类型, default、primary、success、warning、danger、info", example = "default")
    val colorType: String? = null,

    @Schema(description = "CSS 样式", example = "btn-visible")
    val cssClass: String? = null,

    @Schema(description = "备注", example = "备注")
    val remark: String? = null,

    @Schema(description = "创建时间", example = "2020-10-09 00:00:00")
    @ReverseAutoMapping(dateFormat = "yyyy-MM-dd HH:mm:ss")
    var createTime: String,
) : Serializable
