package com.hzwl.iot.module.system.dal.entity.users

import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.common.enums.SexEnum
import com.hzwl.iot.framework.mybatis.core.entity.TenantEntity
import com.hzwl.iot.module.system.dal.mapper.users.UserMapper
import com.mybatisflex.annotation.Id
import com.mybatisflex.annotation.KeyType
import com.mybatisflex.annotation.Table
import com.mybatisflex.core.keygen.KeyGenerators
import com.mybatisflex.kotlin.extensions.db.mapper
import java.time.LocalDateTime


@Table("system_users", comment = "用户表")
data class User(
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    var id: Long?,
    /**
     * 用户名
     */
    val username: String,
    /**
     * 密码
     */
    var password: String?,
    /**
     * 昵称
     */
    val nickname: String,
    /**
     * 备注
     */
    val remark: String?,

    /**
     * 部门编号
     */
    val deptId: Long?,
    /**
     * 邮箱
     */
    val email: String?,
    /**
     * 手机
     */
    val phone: String,
    /**
     * 性别
     */
    var sex: SexEnum?,
    /**
     * 头像
     */
    val avatar: String?,
    /**
     * 状态
     */
    val status: CommonStatusEnum,
    /**
     * 最后登录IP
     */
    val loginIp: String?,
    /**
     * 最后登录时间
     */
    val loginDate: LocalDateTime?
) : TenantEntity() {
    companion object : UserMapper by mapper()
}
