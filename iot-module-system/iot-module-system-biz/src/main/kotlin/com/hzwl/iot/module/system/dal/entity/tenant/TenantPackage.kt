package com.hzwl.iot.module.system.dal.entity.tenant

import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.framework.mybatis.core.entity.BaseEntity
import com.hzwl.iot.framework.mybatis.handler.LongCommaSplitTypeHandler
import com.hzwl.iot.module.system.dal.mapper.tenant.TenantPackageMapper
import com.mybatisflex.annotation.Column
import com.mybatisflex.annotation.Id
import com.mybatisflex.annotation.KeyType
import com.mybatisflex.annotation.Table
import com.mybatisflex.core.keygen.KeyGenerators
import com.mybatisflex.kotlin.extensions.db.mapper

@Table("system_tenant_package", comment = "租户套餐表")
data class TenantPackage(
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    var id: Long? = null,

    /**
     * 套餐名
     */
    val name: String,

    /**
     * 状态
     */
    var status: CommonStatusEnum,

    /**
     * 备注
     */
    val remark: String?,

    /**
     * 菜单权限
     */
    @Column(typeHandler = LongCommaSplitTypeHandler::class)
    val menuIds: List<Long>?
) : BaseEntity() {
    companion object : TenantPackageMapper by mapper()
}
