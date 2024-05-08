package com.hzwl.framework.web.utils

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.web.bind.annotation.RequestBody
import java.lang.reflect.Method

object AspectUtil {
    /**
     * 获取被拦截方法对象
     */
    fun getMethod(pjp: ProceedingJoinPoint): Method {
        // 如果转换成功，则 sig 就是非空的，可以直接访问 sig.method。如果转换失败，则 sig 为 null，使用空合并运算符 ?: 来抛出异常。
        val sig = pjp.signature as? MethodSignature
        return sig?.method ?: throw IllegalArgumentException("It's not method")
    }

    /**
     * 获取请求体
     */
    fun getBody(pjp: ProceedingJoinPoint): Any? {
        val args = pjp.args
        val method = getMethod(pjp)
        if (args.isNotEmpty()) {
            val parameterAnnotations = method.parameterAnnotations

            /**
             * 1. 遍历每个参数 拿到每个参数的注解列表
             * 2. 遍历每个参数的注解列表
             * 3. 如果有注解RequestBody 则返回这个参数
             */
            for (count in parameterAnnotations.indices) {
                for (annotation in parameterAnnotations[count]) {
                    if (annotation is RequestBody) {
                        return args[count]
                    }
                }
            }
        }
        return null
    }
}

