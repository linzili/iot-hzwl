package com.hzwl.framework.common.exception

/**
 * 业务逻辑异常 Exception
 * @author lin
 */
class ServiceException(
    val code: Int = 500,
    override val message: String = "error"
) : RuntimeException() {
    constructor(message: String) : this(500, message)
    constructor(errorCode: ErrorCode) : this(errorCode.code, errorCode.message)
}
