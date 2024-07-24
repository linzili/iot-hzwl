package com.hzwl.iot.module.system.service.auth

import com.hzwl.iot.module.system.controller.login.vo.AccountLoginReqVO
import com.hzwl.iot.module.system.controller.login.vo.CaptchaVO
import com.hzwl.iot.module.system.controller.login.vo.LoginRespVO

interface AuthService {
    /**
     * 获取验证码
     *
     * @return 验证码
     */
    fun getCaptcha(): CaptchaVO

    /**
     * 账号登陆
     *
     * @param reqDTO
     * @return
     */
    fun accountLogin(reqVo: AccountLoginReqVO): LoginRespVO
}

