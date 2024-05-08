package com.hzwl.iot.common.exception


/**
 * 错误码对象
 *
 * 全局错误码，占用 [0, 999], 参见 {@link GlobalErrorCodeConstants}
 * 业务异常错误码，占用 [1 000 000 000, +∞)，参见 {@link ServiceErrorCodeRange}
 *
 */
data class ErrorCode(
    val code: Int,
    val message: String,
)
