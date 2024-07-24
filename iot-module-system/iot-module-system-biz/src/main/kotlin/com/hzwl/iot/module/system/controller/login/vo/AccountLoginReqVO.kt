package com.hzwl.iot.module.system.controller.login.vo

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "管理后台 - 账号密码登录 Request VO")
data class AccountLoginReqVO(
    @Schema(description = "用户账号", required = true, example = "hzwl")
    var username: String?,

    @Schema(description = "密码", required = true, example = "hzwl123456")
    var password: String?,

    @Schema(description = "验证码的会话编号", required = true, example = "1024")
    val sessionId: Long?,

    @Schema(description = "验证码", required = true, example = "su8z")
    val captcha: String?,

    @Schema(description = "登陆租户的域名", required = true, example = "hze2.com")
    val hostname: String?
)
