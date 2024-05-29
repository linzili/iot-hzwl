package com.hzwl.iot.framework.mybatis.core.handle

import com.mybatisflex.core.tenant.TenantFactory

/**
 * 多租户配置
 * @author lin
 */
class MyTenantFactory : TenantFactory {


    @Deprecated("Deprecated in Java")
    override fun getTenantIds(): Array<Any> {
        return arrayOf(1)
    }

    override fun getTenantIds(tableName: String): Array<Any> {
        return arrayOf(1)
    }
}
