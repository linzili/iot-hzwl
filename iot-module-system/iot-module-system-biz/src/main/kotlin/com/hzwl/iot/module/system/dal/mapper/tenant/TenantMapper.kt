package com.hzwl.iot.module.system.dal.mapper.tenant

import com.hzwl.iot.common.pojo.PageResult
import com.hzwl.iot.framework.mybatis.extensions.between
import com.hzwl.iot.framework.mybatis.extensions.like
import com.hzwl.iot.framework.mybatis.extensions.paginateAs
import com.hzwl.iot.module.system.controller.tenant.vo.tenant.TenantPageReqVO
import com.hzwl.iot.module.system.controller.tenant.vo.tenant.TenantRespVO
import com.hzwl.iot.module.system.dal.entity.tenant.Tenant
import com.mybatisflex.core.BaseMapper
import com.mybatisflex.kotlin.extensions.kproperty.eq
import com.mybatisflex.kotlin.extensions.kproperty.unaryPlus
import com.mybatisflex.kotlin.extensions.wrapper.andAll
import org.apache.ibatis.annotations.Mapper

@Mapper
@JvmDefaultWithCompatibility
interface TenantMapper : BaseMapper<Tenant> {
    fun selectPage(pageReqVO: TenantPageReqVO): PageResult<TenantRespVO> =
        paginateAs<TenantRespVO>(pageReqVO) {
            andAll(
                Tenant::name like pageReqVO.name,
                Tenant::contactName like pageReqVO.contactName,
                Tenant::status eq pageReqVO.status,
                Tenant::website like pageReqVO.website,
                Tenant::packageId eq pageReqVO.packageId,
                Tenant::createTime between pageReqVO.createTime
            )
            orderBy(+Tenant::id)
        }
}
