package com.hzwl.iot.module.system.controller.tenant.vo.packages

import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.framework.mybatis.handler.LongCommaSplitTypeHandler
import com.hzwl.iot.module.system.dal.entity.tenant.TenantPackage
import com.mybatisflex.annotation.Column
import io.github.linpeilie.annotations.AutoMapper
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED

@Schema(description = "管理后台 - 租户套餐 Response VO")
@AutoMapper(target = TenantPackage::class)
data class TenantPackageRespVO(
    @Schema(description = "套餐编号", requiredMode = REQUIRED, example = "1024")
    val id: Long,

    @Schema(description = "套餐名", requiredMode = REQUIRED, example = "VIP")
    val name: String,

    @Schema(description = "状态,参见 CommonStatusEnum 枚举类", requiredMode = REQUIRED, example = "1")
    val status: CommonStatusEnum,

    @Schema(description = "备注", example = "好")
    val remark: String,

    @Schema(description = "菜单编号列表", requiredMode = REQUIRED, example = "1024")
    @Column(typeHandler = LongCommaSplitTypeHandler::class)
    val menuIds: List<Long>? = null,

    @Schema(description = "创建时间", requiredMode = REQUIRED, example = "2020-10-09 00:00:00")
    val createTime: String
)
