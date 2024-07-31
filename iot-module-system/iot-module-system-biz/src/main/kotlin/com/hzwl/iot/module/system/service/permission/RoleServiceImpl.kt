package com.hzwl.iot.module.system.service.permission

import RoleCodeEnum
import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.common.exception.util.ServiceExceptionUtil.exception
import com.hzwl.iot.common.extensions.convert
import com.hzwl.iot.common.pojo.PageResult
import com.hzwl.iot.framework.mybatis.extensions.selectListByQueryAs
import com.hzwl.iot.module.system.controller.permission.vo.role.RolePageReqVO
import com.hzwl.iot.module.system.controller.permission.vo.role.RoleRespVO
import com.hzwl.iot.module.system.controller.permission.vo.role.RoleSaveReqVO
import com.hzwl.iot.module.system.controller.permission.vo.role.RoleSimpleRespVO
import com.hzwl.iot.module.system.dal.entity.permission.Role
import com.hzwl.iot.module.system.dal.enums.permission.role.DataScopeType
import com.hzwl.iot.module.system.dal.enums.permission.role.RoleType
import com.hzwl.iot.module.system.dal.mapper.permission.RoleMapper
import com.hzwl.iot.module.system.enums.ErrorCodeConstants
import com.hzwl.iot.module.system.enums.ErrorCodeConstants.ROLE_ADMIN_CODE_ERROR
import com.hzwl.iot.module.system.enums.ErrorCodeConstants.ROLE_NOT_EXISTS
import com.mybatisflex.kotlin.extensions.kproperty.eq
import com.mybatisflex.kotlin.extensions.kproperty.unaryPlus
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional
class RoleServiceImpl : ServiceImpl<RoleMapper, Role>(), RoleService {
    override fun createRole(createReqVo: RoleSaveReqVO): Long {
        // 1. 校验角色
        validateRoleDuplicate(null, createReqVo.name!!, createReqVo.code!!)

        // 插入到数据库
        return convert(createReqVo, Role::class.java).run {
            id = null
            type = RoleType.CUSTOM
            dataScope = DataScopeType.ALL
            status = CommonStatusEnum.ENABLE
            save(this)
            this.id!!
        }
    }

    override fun updateRole(updateReqVo: RoleSaveReqVO): Boolean {
        validateRoleForUpdate(updateReqVo.id!!)
        validateRoleDuplicate(updateReqVo.id, updateReqVo.name!!, updateReqVo.code!!)

        return convert(updateReqVo, Role::class.java).run {
            updateById(this)
        }
    }


    override fun deleteRole(id: Long): Boolean {
        validateRoleForUpdate(id)
        return removeById(id)
    }

    override fun getRolePage(pageReqVO: RolePageReqVO): PageResult<RoleRespVO>  =
        mapper.selectPage(pageReqVO)

    override fun getRoleById(id: Long): Role = getById(id) ?: throw exception(ROLE_NOT_EXISTS)

    override fun getSimpleRoleList(): List<RoleSimpleRespVO> =
        mapper.selectListByQueryAs<RoleSimpleRespVO> {
            where(Role::status eq CommonStatusEnum.ENABLE)
            orderBy(+Role::sort)
        }


    private fun validateRoleForUpdate(id: Long): Role =
        getById(id)?.also {
            if (it.type == RoleType.SYSTEM) {
                throw exception(ErrorCodeConstants.ROLE_CAN_NOT_UPDATE_SYSTEM_TYPE_ROLE)
            }
        } ?: throw exception(ROLE_NOT_EXISTS)


    private fun validateRoleDuplicate(id: Long?, name: String, code: String) {
        if (RoleCodeEnum.isSuperAdmin(code)) {
            throw exception(ROLE_ADMIN_CODE_ERROR, code)
        }

        mapper.selectOneByCondition(Role::name eq name)?.let {
            if (it.id != id) throw exception(ErrorCodeConstants.ROLE_NAME_DUPLICATE, name)
        }

        mapper.selectOneByCondition(Role::code eq code)?.let {
            if (it.id != id) throw exception(ErrorCodeConstants.ROLE_CODE_DUPLICATE, code)
        }
    }
}
