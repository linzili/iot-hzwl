package com.hzwl.iot.module.system.controller.dict.vo.data

import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.module.system.dal.entity.dict.DictData
import io.github.linpeilie.annotations.AutoMapper
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.io.Serializable

@Schema(description = "管理后台 - 字典数据创建/修改 Request VO")
@AutoMapper(target = DictData::class)
data class DictDataSaveReqVo(

    @Schema(description = "字典数据编号", example = "1024")
    val id: Long?,

    @Schema(description = "排序值", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @field:NotNull(message = "排序值不能为空")
    val sort: Long?,

    @Schema(description = "字典标签", requiredMode = Schema.RequiredMode.REQUIRED, example = "iot")
    @field:NotBlank(message = "字典标签不能为空")
    @field:Size(max = 100, message = "字典标签长度不能超过100个字符")
    val label: String?,

    @Schema(description = "字典值", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @field:NotBlank(message = "字典键值不能为空")
    @field:Size(max = 100, message = "字典键值长度不能超过100个字符")
    val value: String?,

    @Schema(description = "字典类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "sys_common_sex")
    @field:NotBlank(message = "字典类型不能为空")
    @field:Size(max = 100, message = "字典类型长度不能超过100个字符")
    val dictType: String?,

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @field:NotNull(message = "状态不能为空")
    val status: CommonStatusEnum?,

    @Schema(description = "颜色类型, default、primary、success、warning、danger、info", example = "default")
    val colorType: String? = null,

    @Schema(description = "CSS 样式", example = "btn-visible")
    val cssClass: String? = null,

    @Schema(description = "备注", example = "备注")
    val remark: String? = null,
) : Serializable
