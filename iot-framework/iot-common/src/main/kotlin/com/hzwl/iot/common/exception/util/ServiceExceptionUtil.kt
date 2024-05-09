package com.hzwl.iot.common.exception.util

import com.hzwl.iot.common.exception.ErrorCode
import com.hzwl.iot.common.exception.ServiceException
import com.hzwl.iot.common.extensions.log

object ServiceExceptionUtil {

    fun exception(errorCode: ErrorCode): ServiceException {
        return exception0(errorCode.code, errorCode.message)
    }

    fun exception(errorCode: ErrorCode, vararg params: Any?): ServiceException {
        return exception0(errorCode.code, errorCode.message, params)
    }

    fun exception0(code: Int, messagePattern: String, vararg params: Any?): ServiceException {
        val message: String = doFormat(code, messagePattern, *params)
        return ServiceException(code, message)
    }

    // ========== 格式化方法 ==========
    fun doFormat(code: Int, messagePattern: String, vararg params: Any?): String {
        val sbuf = StringBuilder(messagePattern.length + 50)
        var i = 0
        var j: Int
        var l = 0
        while (l < params.size) {
            j = messagePattern.indexOf("{}", i)
            if (j == -1) {
                log.error("[doFormat][参数过多：错误码({})|错误内容({})|参数({})", code, messagePattern, params)
                if (i == 0) {
                    return messagePattern
                } else {
                    sbuf.append(messagePattern.substring(i))
                    return sbuf.toString()
                }
            } else {
                sbuf.append(messagePattern, i, j)
                sbuf.append(params[l])
                i = j + 2
            }
            l++
        }
        if (messagePattern.indexOf("{}", i) != -1) {
            log.error("[doFormat][参数过少：错误码({})|错误内容({})|参数({})", code, messagePattern, params)
        }
        sbuf.append(messagePattern.substring(i))
        return sbuf.toString()
    }
}
