package com.hzwl.iot.framework.tenant.core.handle

import com.hzwl.iot.framework.tenant.core.context.TenantContextHolder
import com.mybatisflex.core.tenant.TenantFactory
import org.springframework.stereotype.Component

/**
 * 多租户配置
 * @author lin
 */
@Component
class MyTenantFactory : TenantFactory {


    @Deprecated("Deprecated in Java")
    override fun getTenantIds(): Array<Any> {
        return arrayOf(1)
    }

    override fun getTenantIds(tableName: String): Array<Any> =
        if (TenantContextHolder.isIgnore()) emptyArray()
        else arrayOf(TenantContextHolder.getRequiredTenantId())

}
