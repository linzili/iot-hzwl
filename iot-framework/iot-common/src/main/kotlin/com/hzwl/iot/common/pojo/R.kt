package com.hzwl.iot.common.pojo

import io.swagger.v3.oas.annotations.media.Schema

/**
 * 通用返回
 * @author lin
 */
@Schema(description = "通用返回")
data class R<T>(

    @Schema(description = "状态编码 200-成功，非200 -> 失败", required = true, defaultValue = "200")
    var code: Int = 200,

    @Schema(description = "提示消息", required = true, defaultValue = "ok")
    var msg: String = "ok",

    @Schema(description = "响应数据", required = true)
    var data: T? = null
) {

    companion object {
        /**
         * 成功时返回结果
         */
        fun <T> ok(data: T): R<T> {
            return R(data = data)
        }

        /**
         * 成功时返回结果
         */
        fun ok(): R<Nothing> {
            return R()
        }

        /**
         * 失败时返回结果
         */
        fun fail(msg: String? = null): R<Nothing> {
            return R(code = 500, msg = msg ?: "error")
        }

        fun fail(code: Int, msg: String? = null): R<Nothing> {
            return R(code = code, msg = msg ?: "error")
        }
    }


}


