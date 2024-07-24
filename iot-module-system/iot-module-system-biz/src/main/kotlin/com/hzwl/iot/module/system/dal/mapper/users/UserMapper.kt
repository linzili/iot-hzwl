package com.hzwl.iot.module.system.dal.mapper.users

import com.hzwl.iot.common.pojo.PageResult
import com.hzwl.iot.framework.mybatis.extensions.between
import com.hzwl.iot.framework.mybatis.extensions.like
import com.hzwl.iot.framework.mybatis.extensions.paginateAs
import com.hzwl.iot.module.system.controller.users.vo.UserPageReqVO
import com.hzwl.iot.module.system.controller.users.vo.UserRespVO
import com.hzwl.iot.module.system.dal.entity.users.User
import com.mybatisflex.core.BaseMapper
import com.mybatisflex.kotlin.extensions.kproperty.eq
import com.mybatisflex.kotlin.extensions.kproperty.unaryPlus
import com.mybatisflex.kotlin.extensions.wrapper.andAll
import org.apache.ibatis.annotations.Mapper


@Mapper
@JvmDefaultWithCompatibility
interface UserMapper : BaseMapper<User> {
    fun selectPage(pageReqVO: UserPageReqVO): PageResult<UserRespVO> =
        paginateAs<UserRespVO>(pageReqVO) {
            andAll(
                User::username like pageReqVO.username,
                User::status eq pageReqVO.status,
                User::phone like pageReqVO.phone,
                User::createTime between pageReqVO.createTime
            )
            orderBy(+User::id)
        }
}
