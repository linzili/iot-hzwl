package com.hzwl.iot.module.system.controller.dept.vo

import com.hzwl.iot.common.enums.CommonStatusEnum
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "管理后台 - 部门分页 Request VO")
data class DeptListReqVO(

    @Schema(description = "部门名称,模糊匹配", example = "hzwl")
    var name: String? = null,


    @Schema(description = "部门状态（0正常 1停用）,参见 CommonStatusEnum 枚举类", example = "1")
    var status: CommonStatusEnum? = null,

    )
