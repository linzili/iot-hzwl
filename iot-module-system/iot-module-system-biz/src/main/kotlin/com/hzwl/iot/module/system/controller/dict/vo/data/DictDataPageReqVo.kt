package com.hzwl.iot.module.system.controller.dict.vo.data

import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.common.pojo.PageParam
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@Schema(description = "管理后台 - 字典数据分页列表 Request VO")
data class DictDataPageReqVo(

    @Schema(description = "字典标签 模糊匹配", example = "iot")
    @field:Size(max = 100, message = "字典标签长度不能超过100个字符")
    val label: String?,

    @Schema(description = "字典类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "sys_common_sex")
    @field:NotBlank(message = "字典类型不能为空")
    @field:Size(max = 100, message = "字典类型长度不能超过100个字符")
    val dictType: String?,

    @Schema(description = "展示状态", example = "1")
    val status: CommonStatusEnum?,
) : PageParam()
