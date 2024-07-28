package com.hzwl.iot.module.system.service.tenant

import com.hzwl.iot.common.pojo.PageResult
import com.hzwl.iot.module.system.controller.tenant.vo.packages.TenantPackagePageReqVO
import com.hzwl.iot.module.system.controller.tenant.vo.packages.TenantPackageRespVO
import com.hzwl.iot.module.system.controller.tenant.vo.packages.TenantPackageSaveReqVO
import com.hzwl.iot.module.system.controller.tenant.vo.packages.TenantPackageSimpleRespVO
import com.hzwl.iot.module.system.dal.entity.tenant.TenantPackage
import com.mybatisflex.core.service.IService

interface TenantPackageService : IService<TenantPackage> {

    /**
     * 创建租户套餐
     *
     * @param createReqVo 创建租户套餐信息
     * @return 租户套餐编号
     */
    fun createTenantPackage(createReqVo: TenantPackageSaveReqVO): Long

    /**
     * 更新租户套餐
     *
     * @param updateReqVo 更新租户套餐信息
     * @return 是否成功
     */
    fun updateTenantPackage(updateReqVo: TenantPackageSaveReqVO): Boolean

    /**
     * 删除租户套餐
     *
     * @param id 租户套餐编号
     * @return 是否成功
     */
    fun deleteTenantPackage(id: Long): Boolean

    /**
     * 获得租户套餐分页
     *
     * @param pageReqVO 分页查询
     * @return 租户套餐分页
     */
    fun getTenantPackagePage(pageReqVO: TenantPackagePageReqVO): PageResult<TenantPackageRespVO>

    /**
     * 获得租户套餐列表
     *
     * @return 租户套餐列表
     */
    fun getSimpleTenantPackageList(): List<TenantPackageSimpleRespVO>

    /**
     * 获得租户套餐
     *
     * @param id 租户套餐编号
     * @return 租户套餐
     */
    fun getTenantPackageById(id: Long): TenantPackage

    /**
     * 校验租户套餐
     *
     * @param id 租户套餐编号
     */
    fun validateTenantPackage(id: Long): TenantPackage


}
