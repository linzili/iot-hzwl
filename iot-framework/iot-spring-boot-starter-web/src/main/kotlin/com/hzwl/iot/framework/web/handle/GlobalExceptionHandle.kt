package com.hzwl.iot.framework.web.handle

import com.fasterxml.jackson.databind.exc.MismatchedInputException
import com.hzwl.iot.common.exception.ServiceException
import com.hzwl.iot.common.exception.enums.GlobalErrorCodeConstants.INTERNAL_SERVER_ERROR
import com.hzwl.iot.common.extensions.log
import com.hzwl.iot.framework.web.pojo.R
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.ConstraintViolation
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.servlet.resource.NoResourceFoundException

/**
 * 全局异常处理器，将 Exception 翻译成 R
 * @author lin
 */
@RestControllerAdvice
class GlobalExceptionHandle {

    /**
     * 处理 SpringMVC 请求参数缺失
     *
     * 例如说，接口上设置了 @RequestParam("xx") 参数，结果并未传递 xx 参数
     */
    @ExceptionHandler(value = [MissingServletRequestParameterException::class])
    fun missingServletRequestParameterExceptionHandler(ex: MissingServletRequestParameterException): R<Nothing> {
        log.warn("[missingServletRequestParameterExceptionHandler]", ex)
        return R.fail("请求参数缺失:${ex.parameterName}")
    }

    /**
     * 处理 SpringMVC 请求参数类型错误
     *
     * 例如说，接口上设置了 @RequestParam("xx") 参数为 Integer，结果传递 xx 参数类型为 String
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun methodArgumentTypeMismatchExceptionHandler(ex: MethodArgumentTypeMismatchException): R<*> {
        log.warn("[missingServletRequestParameterExceptionHandler]", ex)
        return R.fail("请求参数类型错误:${ex.message}")
    }

    /**
     * 处理 SpringMVC 参数校验不正确
     */
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidExceptionExceptionHandler(ex: MethodArgumentNotValidException): R<*> {
        log.warn("[methodArgumentNotValidExceptionExceptionHandler]", ex)
        return R.fail("请求参数不正确")
    }

    /**
     * 处理 SpringMVC 参数绑定不正确，本质上也是通过 Validator 校验
     */
    @ExceptionHandler(BindException::class)
    fun bindExceptionHandler(ex: BindException): R<*> {
        log.warn("[handleBindException]", ex)
        return R.fail("请求参数不正确")
    }

    /**
     * 处理 SpringMVC 请求参数解析失败
     */
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun httpMessageNotReadableExceptionHandler(ex: HttpMessageNotReadableException): R<*> {
        log.warn("[httpMessageNotReadableExceptionHandler]", ex.cause)
        if (ex.cause is MismatchedInputException) {
            return R.fail("请求体类型错误")
        }
        return R.fail("请求体不能为空")
    }

    /**
     * 处理 Validator 校验不通过产生的异常
     */
    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun constraintViolationExceptionHandler(ex: ConstraintViolationException): R<*> {
        log.warn("[constraintViolationExceptionHandler]", ex)
        val constraintViolation: ConstraintViolation<*> = ex.constraintViolations.iterator().next()
        return R.fail("请求参数不正确:${constraintViolation.message}")
    }


    /**
     * 处理业务异常 ServiceException
     *
     * 例如说，商品库存不足，用户手机号已存在。
     */
    @ExceptionHandler(value = [ServiceException::class])
    fun serviceExceptionHandler(ex: ServiceException): R<*> {
        log.info("[serviceExceptionHandler]", ex)
        return R.fail(ex.code, ex.message)
    }

    @ExceptionHandler(NoResourceFoundException::class)
    fun handle(exception: NoResourceFoundException): R<*> {
        log.error("请求资源不存在 -> $exception")
        return R.fail(HttpStatus.NOT_FOUND.value(), "请求资源不存在:${exception.resourcePath}")
    }

    /**
     * 处理系统异常，兜底处理所有的一切
     */
    @ExceptionHandler(value = [Exception::class])
    fun defaultExceptionHandler(req: HttpServletRequest?, ex: Throwable?): R<*> {
        log.error("[defaultExceptionHandler]", ex)
        return R.fail(INTERNAL_SERVER_ERROR.code, INTERNAL_SERVER_ERROR.message)
    }
}
