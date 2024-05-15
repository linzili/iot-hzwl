package com.hzwl.iot.framework.mybatis.core.entity

/**
 * 租户实体对象
 * @author lin
 */
open class TenantEntity<PK>(
    /**
     * 租户id
     */
    val tenantId: Long? = null,
) : BaseEntity<PK>()
