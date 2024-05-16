package com.hzwl.iot.framework.web.aspect

import com.hzwl.iot.common.exception.ServiceException
import jakarta.validation.Validator
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.stereotype.Component

/**
 * 请求参数校验切面，统一对Controller中的参数进行校验，在Controller方法中无需单独处理
 */
@Aspect
@EnableAspectJAutoProxy
@Component
class ValidatedAspect(val validator: Validator) {

    @Around("execution(* com.hzwl..controller..*Controller.*(..))")
    fun around(proceedingJoinPoint: ProceedingJoinPoint): Any {

        proceedingJoinPoint.args.forEach { arg ->
            // 校验参数
            val validateResult = validator.validate(arg)
            if (validateResult.isNotEmpty()) {
                // 校验未通过，抛出一场，响应状态码为400
                val info = validateResult.joinToString(",") { it.message }
                throw ServiceException(400, "请求参数不正确:$info")
            }
        }

        // 校验通过，执行原方法
        return proceedingJoinPoint.proceed(proceedingJoinPoint.args)
    }
}
