package com.hzwl.iot.module.system.dal.entity.tenant

import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.framework.mybatis.core.entity.BaseEntity
import com.hzwl.iot.module.system.dal.mapper.tenant.TenantMapper
import com.mybatisflex.annotation.Id
import com.mybatisflex.annotation.KeyType
import com.mybatisflex.annotation.Table
import com.mybatisflex.core.keygen.KeyGenerators
import com.mybatisflex.kotlin.extensions.db.mapper
import java.time.LocalDateTime


@Table("system_tenant", comment = "租户表")
data class Tenant(
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    var id: Long? = null,

    /**
     * 租户名称
     */
    val name: String,

    /**
     * 联系人用户编号
     */
    var contactUserId: Long,

    /**
     * 联系人名称
     */
    val contactName: String,

    /**
     * 联系人手机
     */
    val contactPhone: String,

    /**
     * 状态（0正常 1停用）
     * @see CommonStatusEnum
     */
    var status: CommonStatusEnum,

    /**
     * 租户域名
     */
    val website: String,

    /**
     * 租户套餐编号
     */
    val packageId: Long,

    /**
     * 过期时间
     */
    val expireTime: LocalDateTime,

    /**
     * 账号数量
     */
    val accountCount: Int

) : BaseEntity() {
    companion object : TenantMapper by mapper()
}
