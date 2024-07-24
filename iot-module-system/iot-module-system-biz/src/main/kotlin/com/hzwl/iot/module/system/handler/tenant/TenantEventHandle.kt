package com.hzwl.iot.module.system.handler.tenant

import com.hzwl.iot.common.exception.util.ServiceExceptionUtil.exception
import com.hzwl.iot.framework.mybatis.events.EntityDeleteEvent
import com.hzwl.iot.module.system.dal.entity.tenant.Tenant
import com.hzwl.iot.module.system.dal.entity.tenant.TenantPackage
import com.hzwl.iot.module.system.enums.ErrorCodeConstants.TENANT_PACKAGE_HAS_TENANT
import com.mybatisflex.kotlin.extensions.kproperty.eq
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class TenantEventHandle {

    @EventListener(condition = "#event.entityType == T(com.hzwl.iot.module.system.dal.entity.tenant.TenantPackage)")
    fun handleDeleteTenantPackage(event: EntityDeleteEvent<TenantPackage>) {
        if(Tenant.selectCountByCondition(Tenant::packageId eq event.entity.id)>0)
            throw exception(TENANT_PACKAGE_HAS_TENANT)
    }
}
