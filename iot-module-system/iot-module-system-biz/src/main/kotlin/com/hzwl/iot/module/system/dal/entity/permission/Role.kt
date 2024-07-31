package com.hzwl.iot.module.system.dal.entity.permission

import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.framework.mybatis.core.entity.TenantEntity
import com.hzwl.iot.framework.mybatis.handler.LongCommaSplitTypeHandler
import com.hzwl.iot.module.system.dal.enums.permission.role.DataScopeType
import com.hzwl.iot.module.system.dal.enums.permission.role.RoleType
import com.hzwl.iot.module.system.dal.mapper.permission.RoleMapper
import com.mybatisflex.annotation.Column
import com.mybatisflex.annotation.Id
import com.mybatisflex.annotation.KeyType
import com.mybatisflex.annotation.Table
import com.mybatisflex.core.keygen.KeyGenerators
import com.mybatisflex.kotlin.extensions.db.mapper

@Table("system_role", comment = "角色表")
data class Role(
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    var id: Long?,

    /**
     * 角色名称
     */
    val name: String,

    /**
     * 角色标识
     */
    val code: String,

    /**
     * 角色排序
     */
    val sort: Int,

    /**
     * 数据范围
     */
    var dataScope: DataScopeType?,

    /**
     * 数据范围（指定部门数组）
     */
    @Column(typeHandler = LongCommaSplitTypeHandler::class)
    val dataScopeDeptIds: Set<Long>?,

    /**
     * 角色状态（0正常 1停用）
     */
    var status: CommonStatusEnum?,


    /**
     * 角色类型（1：系统 2：自定义）
     */
    var type: RoleType?,

    /**
     * 备注
     */
    val remark: String?

) : TenantEntity() {
    companion object : RoleMapper by mapper()
}
