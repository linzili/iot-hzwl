package com.hzwl.iot.module.system.enums

import com.hzwl.iot.common.exception.ErrorCode

object ErrorCodeConstants {

    // ========== 字典类型 1-002-006-000 ==========
    val DICT_TYPE_NOT_EXISTS: ErrorCode = ErrorCode(1002006001, "当前字典类型不存在")
    val DICT_TYPE_NOT_ENABLE: ErrorCode = ErrorCode(1002006002, "字典类型不处于开启状态，不允许选择")
    val DICT_TYPE_NAME_DUPLICATE: ErrorCode = ErrorCode(1002006003, "已经存在该名字的字典类型")
    val DICT_TYPE_TYPE_DUPLICATE: ErrorCode = ErrorCode(1002006004, "已经存在该类型的字典类型")
    val DICT_TYPE_HAS_CHILDREN: ErrorCode = ErrorCode(1002006005, "无法删除，该字典类型还有字典数据")

    // ========== 字典数据 1-002-007-000 ==========
    val DICT_DATA_NOT_EXISTS: ErrorCode = ErrorCode(1_002_007_001, "当前字典数据不存在")
    val DICT_DATA_NOT_ENABLE: ErrorCode = ErrorCode(1_002_007_002, "字典数据({})不处于开启状态，不允许选择")
    val DICT_DATA_VALUE_DUPLICATE: ErrorCode = ErrorCode(1_002_007_003, "已经存在该值的字典数据")

}
