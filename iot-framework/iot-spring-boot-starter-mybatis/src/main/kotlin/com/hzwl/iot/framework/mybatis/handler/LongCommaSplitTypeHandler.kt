package com.hzwl.iot.framework.mybatis.handler

class LongCommaSplitTypeHandler : CommaSplitTypeHandler<Long>() {
    override fun convertToType(value: String): Long {
        return value.toLong()
    }
}
