package com.hzwl.iot.framework.mybatis.handler

class StringCommaSplitTypeHandler : CommaSplitTypeHandler<String>() {
    override fun convertToType(value: String): String {
        return value
    }
}

