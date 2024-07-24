package com.hzwl.iot.framework.mybatis.handler

import com.hzwl.iot.common.exception.ServiceException
import com.hzwl.iot.common.exception.enums.GlobalErrorCodeConstants
import com.hzwl.iot.common.extensions.log
import com.hzwl.iot.common.pojo.R
import org.apache.ibatis.builder.BuilderException
import org.mybatis.spring.MyBatisSystemException
import org.springframework.core.annotation.Order
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.reflect.InvocationTargetException

/**
 * 全局异常处理器，将 Exception 翻译成 R
 * @author lin
 */
@RestControllerAdvice
@Order(Integer.MIN_VALUE)
class MybatisExceptionHandle {


    /**
     * 处理 Mybatis 系统异常
     *
     */
    @ExceptionHandler(value = [MyBatisSystemException::class])
    fun myBatisSystemExceptionHandler(ex: MyBatisSystemException): R<Nothing> {
        log.error("[mybatis] 错误请求:", ex)
        (ex.cause as? BuilderException)?.cause
            ?.takeIf { it is InvocationTargetException }
            ?.let { it as InvocationTargetException }
            ?.targetException
            ?.takeIf { it is ServiceException }
            ?.let {
                it as ServiceException
                return R.fail(it.code, it.message)
            }

        return R.fail(GlobalErrorCodeConstants.DATA_BASES_ERROR.code, GlobalErrorCodeConstants.DATA_BASES_ERROR.message)
    }


}
