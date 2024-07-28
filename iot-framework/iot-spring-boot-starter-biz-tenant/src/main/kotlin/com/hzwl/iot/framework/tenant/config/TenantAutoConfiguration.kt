package com.hzwl.iot.framework.tenant.config

import com.hzwl.iot.common.enums.WebFilterOrderEnum.TENANT_CONTEXT_FILTER
import com.hzwl.iot.framework.tenant.core.web.TenantContextWebFilter
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean

@AutoConfiguration
class TenantAutoConfiguration {

    @Bean
    fun tenantContextWebFilter(): FilterRegistrationBean<TenantContextWebFilter> {
        val registrationBean = FilterRegistrationBean<TenantContextWebFilter>()
        registrationBean.filter = TenantContextWebFilter()
        registrationBean.order = TENANT_CONTEXT_FILTER
        return registrationBean
    }
}
