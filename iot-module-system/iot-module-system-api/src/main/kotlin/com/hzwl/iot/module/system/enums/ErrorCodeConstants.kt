package com.hzwl.iot.module.system.enums

import com.hzwl.iot.common.exception.ErrorCode

object ErrorCodeConstants {

    val DICT_DATA_VALUE_DUPLICATE: ErrorCode = ErrorCode(1002007003, "已经存在相同值的字典数据")
    val DICT_DATA_NOT_EXISTS: ErrorCode = ErrorCode(1002007004, "字典数据不存在")

}
