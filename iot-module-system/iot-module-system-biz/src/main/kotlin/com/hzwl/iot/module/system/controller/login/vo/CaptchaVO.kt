package com.hzwl.iot.module.system.controller.login.vo

import io.swagger.v3.oas.annotations.media.Schema


@Schema(description = "管理后台 - 验证码的 Response VO")
data class CaptchaVO(

    @Schema(description = "验证码的会话编号", required = true, example = "1024")
    val sessionId: Long,

    @Schema(description = "验证码的图片的 Base64", required = true, example = "")
    val imageData: String,
)
