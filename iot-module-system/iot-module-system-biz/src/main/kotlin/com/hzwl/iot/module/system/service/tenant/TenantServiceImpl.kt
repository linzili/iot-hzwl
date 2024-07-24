package com.hzwl.iot.module.system.service.tenant

import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.common.exception.util.ServiceExceptionUtil.exception
import com.hzwl.iot.common.extensions.convert
import com.hzwl.iot.common.pojo.PageResult
import com.hzwl.iot.framework.mybatis.extensions.selectListByConditionAs
import com.hzwl.iot.module.system.controller.tenant.vo.tenant.TenantPageReqVO
import com.hzwl.iot.module.system.controller.tenant.vo.tenant.TenantRespVO
import com.hzwl.iot.module.system.controller.tenant.vo.tenant.TenantSaveReqVO
import com.hzwl.iot.module.system.controller.tenant.vo.tenant.TenantSimpleRespVO
import com.hzwl.iot.module.system.dal.entity.tenant.Tenant
import com.hzwl.iot.module.system.dal.mapper.tenant.TenantMapper
import com.hzwl.iot.module.system.enums.ErrorCodeConstants.TENANT_NAME_DUPLICATE
import com.hzwl.iot.module.system.enums.ErrorCodeConstants.TENANT_NOT_EXISTS
import com.hzwl.iot.module.system.enums.ErrorCodeConstants.TENANT_WEBSITE_DUPLICATE
import com.hzwl.iot.module.system.service.users.UserService
import com.mybatisflex.kotlin.extensions.kproperty.eq
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.stereotype.Service

@Service
class TenantServiceImpl(
    private val tenantPackageService: TenantPackageService,
    private val userService: UserService,
) : ServiceImpl<TenantMapper, Tenant>(), TenantService {


    /**
     * 创建租户
     *
     * @param createReqVo 租户信息
     * @return 租户id
     */
    override fun createTenant(createReqVo: TenantSaveReqVO): Long {
        validateTenantNameUnique(null, createReqVo.name!!)
        validateUserExists(createReqVo.contactUserId!!)
        validateTenantWebsiteUnique(null, createReqVo.website!!)
        tenantPackageService.validateTenantPackageExists(createReqVo.packageId)
        val tenant = convert(createReqVo, Tenant::class.java)
        tenant.id = null
        save(tenant)
        return tenant.id!!
    }

    /**
     * 修改租户
     *
     * @param updateReqVo 租户信息
     * @return 是否成功
     */
    override fun updateTenant(updateReqVo: TenantSaveReqVO): Boolean {
        validateTenantExists(updateReqVo.id)
        validateTenantNameUnique(updateReqVo.id, updateReqVo.name!!)
        validateUserExists(updateReqVo.contactUserId!!)
        validateTenantWebsiteUnique(updateReqVo.id, updateReqVo.website!!)
        tenantPackageService.validateTenantPackageExists(updateReqVo.packageId)

        val tenant = convert(updateReqVo, Tenant::class.java)

        return updateById(tenant)
    }

    /**
     * 删除租户
     *
     * @param id 租户id
     * @return 是否成功
     */
    override fun deleteTenant(id: Long): Boolean {
        validateTenantExists(id)

        return removeById(id)
    }

    /**
     * 获得租户分页列表
     *
     * @param pageReqVO 分页参数
     * @return 租户分页列表
     */
    override fun getTenantPage(pageReqVO: TenantPageReqVO): PageResult<TenantRespVO> = mapper.selectPage(pageReqVO)

    /**
     * 获得租户列表
     *
     * @return 租户列表
     */
    override fun getSimpleTenantList(): List<TenantSimpleRespVO> =
        mapper.selectListByConditionAs<TenantSimpleRespVO> { Tenant::status eq CommonStatusEnum.ENABLE }

    /**
     * 获得租户
     *
     * @param id 租户id
     * @return 租户
     */
    override fun getTenantById(id: Long): Tenant =
        getById(id) ?: throw exception(TENANT_NOT_EXISTS)

    /**
     * 获得租户
     *
     * @param name 租户名称
     * @return 租户
     */
    override fun getTenantByName(name: String): Tenant =
        mapper.selectOneByCondition(Tenant::name eq name) ?: throw exception(TENANT_NOT_EXISTS)

    /**
     * 获得租户
     *
     * @param website 租户域名
     * @return 租户
     */
    override fun getTenantByWebsite(website: String): Tenant =
        mapper.selectOneByCondition(Tenant::website eq website) ?: throw exception(TENANT_NOT_EXISTS)

    private fun validateTenantExists(id: Long?) = id?.let { getById(it) } ?: throw exception(TENANT_NOT_EXISTS)


    private fun validateTenantWebsiteUnique(id: Long?, website: String) =
        mapper.selectOneByCondition(Tenant::website eq website)?.let { tenant ->
            if (tenant.id != id) throw exception(TENANT_WEBSITE_DUPLICATE)
        }

    private fun validateUserExists(userId: Long) {
        userService.validateUserExists(userId)
    }

    private fun validateTenantNameUnique(id: Long?, name: String) =
        mapper.selectOneByCondition(Tenant::name eq name)?.let { tenant ->
            if (tenant.id != id) throw exception(TENANT_NAME_DUPLICATE)
        }
}
