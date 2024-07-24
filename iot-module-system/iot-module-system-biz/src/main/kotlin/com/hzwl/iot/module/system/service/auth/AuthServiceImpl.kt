package com.hzwl.iot.module.system.service.auth

import cn.hutool.captcha.CaptchaUtil
import com.hzwl.iot.common.exception.util.ServiceExceptionUtil.exception
import com.hzwl.iot.common.utils.IdWorker
import com.hzwl.iot.module.system.controller.login.vo.AccountLoginReqVO
import com.hzwl.iot.module.system.controller.login.vo.CaptchaVO
import com.hzwl.iot.module.system.controller.login.vo.LoginRespVO
import com.hzwl.iot.module.system.enums.ErrorCodeConstants.CAPTCHA_CODE_NOT_EXISTS
import com.hzwl.iot.module.system.enums.SystemConstants
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class AuthServiceImpl(
    private val idWorker: IdWorker,
    private val redisTemplate: RedisTemplate<String, Any>
) : AuthService {

    override fun getCaptcha(): CaptchaVO {

        val captcha = CaptchaUtil.createLineCaptcha(100, 30, 4, 5)
        val code = captcha.code
        val imageBase64 = "data:image/png;base64,${captcha.imageBase64}"
        val sessionId = idWorker.nextId()
        redisTemplate.opsForValue().set("${SystemConstants.CHECK_PREFIX}$sessionId", code, 1, TimeUnit.MINUTES)

        return CaptchaVO(sessionId, imageBase64)
    }

    override fun accountLogin(reqVo: AccountLoginReqVO): LoginRespVO {

        // 校验验证码
        val code = redisTemplate.opsForValue().get("${SystemConstants.CHECK_PREFIX}${reqVo.sessionId}")
        if (code != reqVo.captcha) {
            throw exception(CAPTCHA_CODE_NOT_EXISTS)
        }
        // 查询租户信息

        // 查询用户信息

        // 生成 Token

        // 插入用户登录日志

        // 返回结果


        return LoginRespVO()
    }
}
