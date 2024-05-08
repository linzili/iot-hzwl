package com.hzwl.framework.web.handle

import com.hzwl.framework.common.extensions.log
import com.hzwl.framework.web.pojo.R
import jakarta.validation.ConstraintViolation
import jakarta.validation.ConstraintViolationException
import org.springframework.validation.BindException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

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
        return R.fail(String.format("请求参数缺失:%s", ex.parameterName))
    }

    /**
     * 处理 SpringMVC 请求参数类型错误
     *
     * 例如说，接口上设置了 @RequestParam("xx") 参数为 Integer，结果传递 xx 参数类型为 String
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun methodArgumentTypeMismatchExceptionHandler(ex: MethodArgumentTypeMismatchException): R<*> {
        log.warn("[missingServletRequestParameterExceptionHandler]", ex)
        return R.fail(String.format("请求参数类型错误:%s", ex.message))
    }

    /**
     * 处理 SpringMVC 参数校验不正确
     */
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidExceptionExceptionHandler(ex: MethodArgumentNotValidException): R<*> {
        log.warn("[methodArgumentNotValidExceptionExceptionHandler]", ex)
        val fieldError = checkNotNull(ex.bindingResult.fieldError)
        return R.fail(String.format("请求参数不正确:%s", fieldError.defaultMessage))
    }

    /**
     * 处理 SpringMVC 参数绑定不正确，本质上也是通过 Validator 校验
     */
    @ExceptionHandler(BindException::class)
    fun bindExceptionHandler(ex: BindException): R<*> {
        log.warn("[handleBindException]", ex)
        val fieldError = checkNotNull(ex.fieldError)
        return R.fail(String.format("请求参数不正确:%s", fieldError.defaultMessage))
    }

    /**
     * 处理 Validator 校验不通过产生的异常
     */
    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun constraintViolationExceptionHandler(ex: ConstraintViolationException): R<*> {
        log.warn("[constraintViolationExceptionHandler]", ex)
        val constraintViolation: ConstraintViolation<*> = ex.getConstraintViolations().iterator().next()
        return R.fail(
            java.lang.String.format("请求参数不正确:%s", constraintViolation.getMessage())
        )
    }
}
