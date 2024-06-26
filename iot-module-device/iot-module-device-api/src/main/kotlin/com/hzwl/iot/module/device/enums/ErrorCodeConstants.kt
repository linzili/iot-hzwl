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
    val PRODUCT_NOT_EXISTS: ErrorCode = ErrorCode(1003002002, "当前产品不存在")
    val PRODUCT_PUBLISH_ENABLE: ErrorCode = ErrorCode(1003002003, "当前产品已发布")
    val PRODUCT_PUBLISH_DISABLE: ErrorCode = ErrorCode(1003002004, "当前产品未发布")
    val PRODUCT_EXISTS_DEVICE: ErrorCode = ErrorCode(1003002005, "当前产品下存在设备")


    // ========== 设备 1-003-003-000 ==========
    val DEVICE_DUPLICATE: ErrorCode = ErrorCode(1003003001, "已经存在该编号的产品")
    val DEVICE_NAME_DUPLICATE: ErrorCode = ErrorCode(1003003002, "已经存在该名称的产品")
    val DEVICE_NOT_EXISTS: ErrorCode = ErrorCode(1003003003, "当前设备不存在")
    val DEVICE_ENABLE: ErrorCode = ErrorCode(1003003004, "无法删除，当前设备启用中")
}
