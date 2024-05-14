package com.hzwl.iot.module.device.enums

import com.hzwl.iot.common.exception.ErrorCode

object ErrorCodeConstants {
    // ========== 产品分类 1-003-001-000 ==========
    val PRODUCT_CATEGORY_NAME_DUPLICATE: ErrorCode = ErrorCode(1003001001, "已经存在该名称的产品分类")
    val PRODUCT_CATEGORY_NOT_EXISTS: ErrorCode = ErrorCode(1003001002, "当前产品分类不存在")
    val PRODUCT_CATEGORY_PARENT_NOT_EXISTS: ErrorCode = ErrorCode(1003001003, "当前父级产品分类不存在")
    val PRODUCT_CATEGORY_CHILDREN_EXISTS: ErrorCode = ErrorCode(1003001004, "无法删除，当前产品分类存在子级")

    // ========== 产品分类 1-003-002-000 ==========
    val PRODUCT_NAME_DUPLICATE: ErrorCode = ErrorCode(1003002001, "已经存在该名称的产品")
}
