package com.hzwl.iot.module.system.enums

import com.hzwl.iot.common.exception.ErrorCode

object ErrorCodeConstants {

    // ========== 字典数据 1-002-007-000 ==========
    val DICT_DATA_NOT_EXISTS: ErrorCode = ErrorCode(1_002_007_001, "当前字典数据不存在")
    val DICT_DATA_NOT_ENABLE: ErrorCode = ErrorCode(1_002_007_002, "字典数据({})不处于开启状态，不允许选择")
    val DICT_DATA_VALUE_DUPLICATE: ErrorCode = ErrorCode(1_002_007_003, "已经存在该值的字典数据")

}
