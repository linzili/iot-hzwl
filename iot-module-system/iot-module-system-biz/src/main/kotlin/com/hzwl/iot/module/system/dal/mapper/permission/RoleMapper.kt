package com.hzwl.iot.module.system.dal.mapper.permission

import com.hzwl.iot.common.pojo.PageResult
import com.hzwl.iot.framework.mybatis.extensions.between
import com.hzwl.iot.framework.mybatis.extensions.like
import com.hzwl.iot.framework.mybatis.extensions.paginateAs
import com.hzwl.iot.module.system.controller.permission.vo.role.RolePageReqVO
import com.hzwl.iot.module.system.controller.permission.vo.role.RoleRespVO
import com.hzwl.iot.module.system.dal.entity.permission.Role
import com.mybatisflex.core.BaseMapper
import com.mybatisflex.kotlin.extensions.kproperty.eq
import com.mybatisflex.kotlin.extensions.kproperty.unaryPlus
import com.mybatisflex.kotlin.extensions.wrapper.andAll
import org.apache.ibatis.annotations.Mapper

@Mapper
@JvmDefaultWithCompatibility
interface RoleMapper : BaseMapper<Role> {
    fun selectPage(pageReqVO: RolePageReqVO): PageResult<RoleRespVO> =
        paginateAs<RoleRespVO>(pageReqVO) {
            andAll(
                Role::name like pageReqVO.name,
                Role::code like pageReqVO.code,
                Role::status eq pageReqVO.status,
                Role::createTime between pageReqVO.createTime
            )
            orderBy(+Role::sort)
        }


}
