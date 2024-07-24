package com.hzwl.iot.framework.mybatis.handler

class IntCommaSplitTypeHandler : CommaSplitTypeHandler<Int>() {
    override fun convertToType(value: String): Int {
        return value.toInt()
    }
}
