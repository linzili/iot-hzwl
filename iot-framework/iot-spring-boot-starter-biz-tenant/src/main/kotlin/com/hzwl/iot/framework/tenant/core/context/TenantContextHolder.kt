package com.hzwl.iot.framework.tenant.core.context

import com.alibaba.ttl.TransmittableThreadLocal

/**
 * 租户上下文
 *
 * @author hzwl
 * @date 2022/07/28
 */
object TenantContextHolder {

    /**
     * 当前租户编号
     */
    private val TENANT_ID: ThreadLocal<Long> = TransmittableThreadLocal()

    /**
     * 是否忽略租户
     */
    private val IGNORE: ThreadLocal<Boolean> = TransmittableThreadLocal()

    /**
     * 获得租户编号
     *
     * @return 租户编号
     */
    fun getTenantId(): Long? = TENANT_ID.get()


    /**
     * 获得租户编号。如果不存在，则抛出 NullPointerException 异常
     *
     * @return 租户编号
     */
    fun getRequiredTenantId(): Long = getTenantId() ?: throw NullPointerException("TenantContextHolder 不存在租户编号！")

    fun setTenantId(tenantId: Long?) = TENANT_ID.set(tenantId)


    fun setIgnore(ignore: Boolean) = IGNORE.set(ignore)


    /**
     * 当前是否忽略租户
     *
     * @return 是否忽略
     */
    fun isIgnore(): Boolean = IGNORE.get() == true

    fun clear() {
        TENANT_ID.remove()
        IGNORE.remove()
    }
}
