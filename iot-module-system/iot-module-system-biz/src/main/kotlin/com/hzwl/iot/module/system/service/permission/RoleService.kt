package com.hzwl.iot.module.system.service.permission

import com.hzwl.iot.common.pojo.PageResult
import com.hzwl.iot.module.system.controller.permission.vo.role.RolePageReqVO
import com.hzwl.iot.module.system.controller.permission.vo.role.RoleRespVO
import com.hzwl.iot.module.system.controller.permission.vo.role.RoleSaveReqVO
import com.hzwl.iot.module.system.controller.permission.vo.role.RoleSimpleRespVO
import com.hzwl.iot.module.system.dal.entity.permission.Role
import com.mybatisflex.core.service.IService

interface RoleService :IService<Role> {
    fun createRole(createReqVo: RoleSaveReqVO): Long
    fun updateRole(updateReqVo: RoleSaveReqVO): Boolean
    fun deleteRole(id: Long): Boolean
    fun getRolePage(pageReqVO: RolePageReqVO): PageResult<RoleRespVO>
    fun getRoleById(id: Long): Role
    fun getSimpleRoleList(): List<RoleSimpleRespVO>
}
