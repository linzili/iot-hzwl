package com.hzwl.iot.module.system.dal.entity.dept

import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.framework.mybatis.core.entity.TenantEntity
import com.hzwl.iot.module.system.dal.mapper.dept.DeptMapper
import com.mybatisflex.annotation.*
import com.mybatisflex.core.keygen.KeyGenerators
import com.mybatisflex.kotlin.extensions.db.mapper

@Table("system_dept")
data class Dept(
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    var id: Long? = null,
    /**
     * 部门名称
     */
    val name: String,
    /**
     * 父部门
     */
    var parentId: Long?,
    /**
     * 排序
     */
    val sort: Int,
    /**
     * 负责人
     */
    val leaderUserId: Long?,
    /**
     * 联系电话
     */
    val phone: String?,
    /**
     * 邮箱
     */
    val email: String?,
    /**
     * 状态
     */
    val status: CommonStatusEnum,

    /**
     * 父部门
     */
    @RelationManyToOne(selfField = "parentId", targetField = "id")
    var parent: Dept? = null,

    /**
     * 子部门
     */
    @RelationOneToMany(selfField = "id", targetField = "parentId")
    var children: List<Dept>? = null
) : TenantEntity() {
    companion object : DeptMapper by mapper() {
        val PARENT_ID_ROOT: Long = 0L
    }
}
