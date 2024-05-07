package com.hzwl.framework.web.pojo

import io.swagger.v3.oas.annotations.media.Schema
import java.io.Serializable

/**
 * 通用返回
 * @author lin
 */
@Schema(name = "result", description = "统一返回")
data class R<T>(

    @Schema(description = "状态码 成功: 200, 失败: 500")
    var code: Int = 200,

    @Schema(description = "消息")
    var msg: String = "success",

    var data: T? = null
) : Serializable {

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


