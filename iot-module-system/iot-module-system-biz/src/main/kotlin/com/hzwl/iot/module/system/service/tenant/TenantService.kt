package com.hzwl.iot.module.system.service.tenant

import com.hzwl.iot.common.pojo.PageResult
import com.hzwl.iot.module.system.controller.tenant.vo.tenant.TenantPageReqVO
import com.hzwl.iot.module.system.controller.tenant.vo.tenant.TenantRespVO
import com.hzwl.iot.module.system.controller.tenant.vo.tenant.TenantSaveReqVO
import com.hzwl.iot.module.system.controller.tenant.vo.tenant.TenantSimpleRespVO
import com.hzwl.iot.module.system.dal.entity.tenant.Tenant
import com.mybatisflex.core.service.IService

interface TenantService : IService<Tenant> {

    /**
     * 创建租户
     *
     * @param createReqVo 租户信息
     * @return 租户id
     */
    fun createTenant(createReqVo: TenantSaveReqVO): Long

    /**
     * 修改租户
     *
     * @param updateReqVo 租户信息
     * @return 是否成功
     */
    fun updateTenant(updateReqVo: TenantSaveReqVO): Boolean

    /**
     * 删除租户
     *
     * @param id 租户id
     * @return 是否成功
     */
    fun deleteTenant(id: Long): Boolean

    /**
     * 获得租户分页列表
     *
     * @param pageReqVO 分页参数
     * @return 租户分页列表
     */
    fun getTenantPage(pageReqVO: TenantPageReqVO): PageResult<TenantRespVO>

    /**
     * 获得租户列表
     *
     * @return 租户列表
     */
    fun getSimpleTenantList(): List<TenantSimpleRespVO>
    /**
     * 获得租户
     *
     * @param id 租户id
     * @return 租户
     */
    fun getTenantById(id: Long): Tenant
    /**
     * 获得租户
     *
     * @param name 租户名称
     * @return 租户
     */
    fun getTenantByName(name: String): Tenant
    /**
     * 获得租户
     *
     * @param website 租户域名
     * @return 租户
     */
    fun getTenantByWebsite(website: String): Tenant


}
