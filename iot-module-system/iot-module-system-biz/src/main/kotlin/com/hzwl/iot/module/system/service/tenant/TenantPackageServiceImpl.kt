package com.hzwl.iot.module.system.service.tenant

import cn.hutool.extra.spring.SpringUtil.publishEvent
import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.common.exception.util.ServiceExceptionUtil.exception
import com.hzwl.iot.common.extensions.convert
import com.hzwl.iot.common.pojo.PageResult
import com.hzwl.iot.framework.mybatis.events.EntityDeleteEvent
import com.hzwl.iot.framework.mybatis.extensions.selectListByConditionAs
import com.hzwl.iot.module.system.controller.tenant.vo.packages.TenantPackagePageReqVO
import com.hzwl.iot.module.system.controller.tenant.vo.packages.TenantPackageRespVO
import com.hzwl.iot.module.system.controller.tenant.vo.packages.TenantPackageSaveReqVO
import com.hzwl.iot.module.system.controller.tenant.vo.packages.TenantPackageSimpleRespVO
import com.hzwl.iot.module.system.dal.entity.tenant.TenantPackage
import com.hzwl.iot.module.system.dal.mapper.tenant.TenantPackageMapper
import com.hzwl.iot.module.system.enums.ErrorCodeConstants.TENANT_PACKAGE_DISABLE
import com.hzwl.iot.module.system.enums.ErrorCodeConstants.TENANT_PACKAGE_NAME_DUPLICATE
import com.hzwl.iot.module.system.enums.ErrorCodeConstants.TENANT_PACKAGE_NOT_EXISTS
import com.mybatisflex.kotlin.extensions.kproperty.eq
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.stereotype.Service

@Service
class TenantPackageServiceImpl : ServiceImpl<TenantPackageMapper, TenantPackage>(), TenantPackageService {
    /**
     * 创建租户套餐
     *
     * @param createReqVo 创建租户套餐信息
     * @return 租户套餐编号
     */
    override fun createTenantPackage(createReqVo: TenantPackageSaveReqVO): Long {
        validateTenantPackageNameUnique(null, createReqVo.name!!)

        val tenantPackage = convert(createReqVo, TenantPackage::class.java)
        tenantPackage.id = null
        save(tenantPackage)
        return tenantPackage.id!!
    }


    /**
     * 更新租户套餐
     *
     * @param updateReqVo 更新租户套餐信息
     * @return 是否成功
     */
    override fun updateTenantPackage(updateReqVo: TenantPackageSaveReqVO): Boolean {
        validateTenantPackageExists(updateReqVo.id)
        validateTenantPackageNameUnique(updateReqVo.id, updateReqVo.name!!)

        val tenantPackage = convert(updateReqVo, TenantPackage::class.java)

        return updateById(tenantPackage)
    }

    /**
     * 删除租户套餐
     *
     * @param id 租户套餐编号
     * @return 是否成功
     */
    override fun deleteTenantPackage(id: Long): Boolean {
        val tenantPackage = validateTenantPackageExists(id)

        publishEvent(EntityDeleteEvent(tenantPackage))

        return removeById(id)
    }


    /**
     * 获得租户套餐分页
     *
     * @param pageReqVO 分页查询
     * @return 租户套餐分页
     */
    override fun getTenantPackagePage(pageReqVO: TenantPackagePageReqVO): PageResult<TenantPackageRespVO> =
        mapper.selectPage(pageReqVO)


    /**
     * 获得租户套餐列表
     *
     * @return 租户套餐列表
     */
    override fun getSimpleTenantPackageList(): List<TenantPackageSimpleRespVO> =
        mapper.selectListByConditionAs<TenantPackageSimpleRespVO> { TenantPackage::status eq CommonStatusEnum.ENABLE }

    /**
     * 获得租户套餐
     *
     * @param id 租户套餐编号
     * @return 租户套餐
     */
    override fun getTenantPackageById(id: Long): TenantPackage =
        getById(id) ?: throw exception(TENANT_PACKAGE_NOT_EXISTS)

    /**
     * 校验租户套餐
     *
     * @param id 租户套餐编号
     */
    override fun validateTenantPackage(id: Long): TenantPackage =
        mapper.selectOneById(id)?.let {
            if (it.status == CommonStatusEnum.DISABLE)
                throw exception(TENANT_PACKAGE_DISABLE, it.name)
            it
        } ?: throw exception(TENANT_PACKAGE_NOT_EXISTS)


    /**
     * 校验租户套餐是否存在
     *
     * @param id 租户套餐编号
     */
    fun validateTenantPackageExists(id: Long?): TenantPackage =
        id?.let { getById(id) } ?: throw exception(TENANT_PACKAGE_NOT_EXISTS)

    private fun validateTenantPackageNameUnique(id: Long?, name: String) =
        mapper.selectOneByCondition(TenantPackage::name eq name)?.let { tenantPackage ->
            if (tenantPackage.id != id) throw exception(TENANT_PACKAGE_NAME_DUPLICATE)
        }

}

