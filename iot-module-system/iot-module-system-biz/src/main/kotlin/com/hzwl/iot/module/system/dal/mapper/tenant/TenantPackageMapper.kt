package com.hzwl.iot.module.system.dal.mapper.tenant

import com.hzwl.iot.common.pojo.PageResult
import com.hzwl.iot.framework.mybatis.extensions.between
import com.hzwl.iot.framework.mybatis.extensions.like
import com.hzwl.iot.framework.mybatis.extensions.paginateAs
import com.hzwl.iot.module.system.controller.tenant.vo.packages.TenantPackagePageReqVO
import com.hzwl.iot.module.system.controller.tenant.vo.packages.TenantPackageRespVO
import com.hzwl.iot.module.system.dal.entity.tenant.TenantPackage
import com.mybatisflex.core.BaseMapper
import com.mybatisflex.kotlin.extensions.kproperty.eq
import com.mybatisflex.kotlin.extensions.kproperty.unaryPlus
import com.mybatisflex.kotlin.extensions.wrapper.andAll
import org.apache.ibatis.annotations.Mapper

@Mapper
@JvmDefaultWithCompatibility
interface TenantPackageMapper : BaseMapper<TenantPackage> {
    fun selectPage(pageReqVO: TenantPackagePageReqVO): PageResult<TenantPackageRespVO> =
        paginateAs<TenantPackageRespVO>(pageReqVO) {
            andAll(
                TenantPackage::name like pageReqVO.name,
                TenantPackage::status eq pageReqVO.status,
                TenantPackage::createTime between pageReqVO.createTime
            )
            orderBy(+TenantPackage::id)
        }


}
