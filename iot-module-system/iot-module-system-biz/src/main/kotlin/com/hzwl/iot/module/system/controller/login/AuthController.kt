package com.hzwl.iot.module.system.controller.login

import com.hzwl.iot.common.pojo.R
import com.hzwl.iot.module.system.controller.login.vo.AccountLoginReqVO
import com.hzwl.iot.module.system.controller.login.vo.CaptchaVO
import com.hzwl.iot.module.system.controller.login.vo.LoginRespVO
import com.hzwl.iot.module.system.service.auth.AuthService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("system/auth")
@Tag(name = "管理后台 - 认证")
class AuthController(
    private val authService: AuthService
) {


    @GetMapping("captcha")
    @Operation(summary = "获取验证码")
    fun getCaptcha(): R<CaptchaVO> = R.ok(authService.getCaptcha())


    @PostMapping("account-login")
    @Operation(summary = "使用账号密码登录")
    fun accountLogin(@RequestBody reqVo: AccountLoginReqVO): R<LoginRespVO> = R.ok(authService.accountLogin(reqVo))
}
