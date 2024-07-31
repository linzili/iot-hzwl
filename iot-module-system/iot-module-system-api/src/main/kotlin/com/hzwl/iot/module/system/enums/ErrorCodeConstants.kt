package com.hzwl.iot.module.system.enums

import com.hzwl.iot.common.exception.ErrorCode

object ErrorCodeConstants {


    // ========== 认证 1-001-001-000 ==========
    val CAPTCHA_CODE_NOT_EXISTS = ErrorCode(1001001001, "验证码错误")

    // ========== 用户 1-001-002-000 ==========
    val USER_NOT_EXISTS = ErrorCode(1001002001, "用户不存在")
    val USER_NAME_DUPLICATE = ErrorCode(1001002002, "用户名重复")
    val USER_PHONE_DUPLICATE = ErrorCode(1001002003, "用户手机号重复")
    val USER_IS_DEPT_LEADER = ErrorCode(1001002004, "用户是部门负责人，不能删除")

    // ========== 角色 1-001-003-000 ==========
    val ROLE_ADMIN_CODE_ERROR = ErrorCode(1001003001, "编码【{}】不能被使用")
    val ROLE_NAME_DUPLICATE = ErrorCode(1001003002, "已经存在名为【{}】的角色")
    val ROLE_CODE_DUPLICATE = ErrorCode(1001003003, "已经存在编码为【{}】的角色")
    val ROLE_NOT_EXISTS = ErrorCode(1001003004, "角色不存在")
    val ROLE_CAN_NOT_UPDATE_SYSTEM_TYPE_ROLE = ErrorCode(1001003005, "不能操作类型为系统内置的角色")


    // ========== 部门模块 1-002-004-000 ==========
    val DEPT_NAME_DUPLICATE: ErrorCode = ErrorCode(1002004000, "已经存在该名字的部门")
    val DEPT_PARENT_NOT_EXITS: ErrorCode = ErrorCode(1002004001, "父级部门不存在")
    val DEPT_NOT_FOUND: ErrorCode = ErrorCode(1002004002, "当前部门不存在")
    val DEPT_EXITS_CHILDREN: ErrorCode = ErrorCode(1002004003, "存在子部门，无法删除")
    val DEPT_PARENT_ERROR: ErrorCode = ErrorCode(1002004004, "不能设置自己为父部门")
    val DEPT_EXISTS_USER: ErrorCode = ErrorCode(1002004005, "部门中存在员工，无法删除")
    val DEPT_NOT_ENABLE: ErrorCode = ErrorCode(1002004006, "部门({})不处于开启状态，不允许选择")
    val DEPT_PARENT_IS_CHILD: ErrorCode = ErrorCode(1002004007, "不能设置自己的子部门为父部门")

    // ========== 字典类型 1-002-006-000 ==========
    val DICT_TYPE_NOT_EXISTS = ErrorCode(1002006001, "当前字典类型不存在")
    val DICT_TYPE_NOT_ENABLE = ErrorCode(1002006002, "字典类型不处于开启状态，不允许选择")
    val DICT_TYPE_NAME_DUPLICATE = ErrorCode(1002006003, "已经存在该名字的字典类型")
    val DICT_TYPE_TYPE_DUPLICATE = ErrorCode(1002006004, "已经存在该类型的字典类型")
    val DICT_TYPE_HAS_CHILDREN = ErrorCode(1002006005, "无法删除，该字典类型还有字典数据")

    // ========== 字典数据 1-002-007-000 ==========
    val DICT_DATA_NOT_EXISTS = ErrorCode(1_002_007_001, "当前字典数据不存在")
    val DICT_DATA_NOT_ENABLE = ErrorCode(1_002_007_002, "字典数据({})不处于开启状态，不允许选择")
    val DICT_DATA_VALUE_DUPLICATE = ErrorCode(1_002_007_003, "已经存在该值的字典数据")

    // ========== 租户 1-002-008-000 ==========
    val TENANT_NAME_DUPLICATE = ErrorCode(1002008001, "租户名称重复")
    val TENANT_WEBSITE_DUPLICATE = ErrorCode(1002008002, "租户域名重复")
    val TENANT_NOT_EXISTS = ErrorCode(1002008003, "租户不存在")
    val TENANT_PACKAGE_HAS_TENANT = ErrorCode(1002008004, "无法删除，该租户套餐下存在租户")
    val CAN_NOT_UPDATE_SYSTEM_TENANT = ErrorCode(1002008005, "系统租户不能进行修改、删除等操作！")

    // ========== 租户套餐 1-002-009-000 ==========
    val TENANT_PACKAGE_NOT_EXISTS = ErrorCode(1002009001, "租户套餐不存在")
    val TENANT_PACKAGE_NAME_DUPLICATE = ErrorCode(1002009002, "租户套餐名称重复")
    val TENANT_PACKAGE_DISABLE = ErrorCode(1002009003, "名字为【{}】的租户套餐已被禁用")


}
