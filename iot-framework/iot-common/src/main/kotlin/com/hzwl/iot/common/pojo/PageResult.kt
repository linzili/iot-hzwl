package com.hzwl.iot.common.pojo

import io.swagger.v3.oas.annotations.media.Schema
import java.io.Serializable

@Schema(description = "分页结果")
data class PageResult<T>(
    val list: List<T>,
    val total: Long,
) : Serializable
