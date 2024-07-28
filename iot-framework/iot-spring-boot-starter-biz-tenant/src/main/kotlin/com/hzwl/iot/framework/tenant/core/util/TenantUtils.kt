package com.hzwl.iot.framework.tenant.core.util

import com.hzwl.iot.framework.tenant.core.context.TenantContextHolder

object TenantUtils {

    const val HEADER_TENANT_ID = "tenant-id"
    /**
     * 使用指定租户，执行对应的逻辑
     *
     * 注意，如果当前是忽略租户的情况下，会被强制设置成不忽略租户
     * 当然，执行完成后，还是会恢复回去
     *
     * @param tenantId 租户编号
     * @param block 逻辑
     */
    inline fun <T> withTenant(tenantId: Long, block: () -> T): T {
        val oldTenantId = TenantContextHolder.getTenantId()
        val oldIgnore = TenantContextHolder.isIgnore()
        return try {
            TenantContextHolder.setTenantId(tenantId)
            TenantContextHolder.setIgnore(false)
            block()
        } finally {
            TenantContextHolder.setTenantId(oldTenantId)
            TenantContextHolder.setIgnore(oldIgnore)
        }
    }

    /**
     * 忽略租户，执行对应的逻辑
     *
     * @param block 逻辑
     */
    inline fun <T> ignoreTenant(block: () -> T): T {
        val oldIgnore = TenantContextHolder.isIgnore()
        return try {
            TenantContextHolder.setIgnore(true)
            block()
        } finally {
            TenantContextHolder.setIgnore(oldIgnore)
        }
    }

    /**
     * 将多租户编号，添加到 header 中
     *
     * @param headers HTTP 请求 headers
     * @param tenantId 租户编号
     */
    fun addTenantHeader(headers: MutableMap<String,String>, tenantId: Long?) {
        tenantId?.let {
            headers[HEADER_TENANT_ID] = it.toString()
        }
    }
}
