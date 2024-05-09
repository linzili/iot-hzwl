package com.hzwl.iot.module.system.controller.dict.vo.type

import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.module.system.dal.entity.dict.DictType
import io.github.linpeilie.annotations.AutoMapper
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@Schema(description = "管理后台 - 字典类型创建/修改 Request VO")
@AutoMapper(target = DictType::class)
data class DictTypeSaveReqVO(
    @Schema(description = "字典类型编号", example = "1024")
    val id: Long? = null,

    @Schema(description = "字典名称", required = true, example = "性别")
    @field:NotBlank(message = "字典名称不能为空")
    @field:Size(max = 100, message = "字典名称长度不能超过100个字符")
    val name: String?,

    @Schema(description = "字典类型", required = true, example = "sys_common_status")
    @field:NotBlank(message = "字典类型不能为空")
    @field:Size(max = 100, message = "字典类型长度不能超过100个字符")
    val type: String?,

    @Schema(description = "状态", required = true, example = "1")
    @field:NotNull(message = "状态不能为空")
    val status: CommonStatusEnum?,

    @Schema(description = "备注", example = "快乐的备注")
    val remark: String? = null,
)
