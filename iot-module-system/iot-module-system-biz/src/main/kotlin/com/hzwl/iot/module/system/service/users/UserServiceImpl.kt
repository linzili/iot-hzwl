package com.hzwl.iot.module.system.service.users

import cn.hutool.extra.spring.SpringUtil.publishEvent
import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.common.enums.SexEnum
import com.hzwl.iot.common.exception.util.ServiceExceptionUtil.exception
import com.hzwl.iot.common.extensions.convert
import com.hzwl.iot.common.pojo.PageResult
import com.hzwl.iot.framework.mybatis.events.EntityDeleteEvent
import com.hzwl.iot.framework.mybatis.extensions.selectListByConditionAs
import com.hzwl.iot.module.system.controller.users.vo.UserPageReqVO
import com.hzwl.iot.module.system.controller.users.vo.UserRespVO
import com.hzwl.iot.module.system.controller.users.vo.UserSaveReqVO
import com.hzwl.iot.module.system.controller.users.vo.UserSimpleRespVO
import com.hzwl.iot.module.system.dal.entity.users.User
import com.hzwl.iot.module.system.dal.mapper.users.UserMapper
import com.hzwl.iot.module.system.enums.ErrorCodeConstants.USER_NAME_DUPLICATE
import com.hzwl.iot.module.system.enums.ErrorCodeConstants.USER_NOT_EXISTS
import com.hzwl.iot.module.system.enums.ErrorCodeConstants.USER_PHONE_DUPLICATE
import com.mybatisflex.kotlin.extensions.kproperty.eq
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val passwordEncoder: PasswordEncoder
) : ServiceImpl<UserMapper, User>(), UserService {
    /**
     * 创建用户
     *
     * @param createReqVo 创建信息
     * @return 用户编号
     */
    override fun createUser(createReqVo: UserSaveReqVO): Long {

        validateUserNameUnique(null, createReqVo.username!!)
        validateUserPhoneUnique(null, createReqVo.phone!!)

        val user = convert(createReqVo, User::class.java)

        user.password = passwordEncoder.encode(user.password)

        if(user.sex == null) user.sex = SexEnum.UNKNOWN

        user.id = null
        save(user)
        return user.id!!
    }

    /**
     * 更新用户
     *
     * @param updateReqVo 更新信息
     * @return 是否更新成功
     */
    override fun updateUser(updateReqVo: UserSaveReqVO): Boolean {
        validateUserExists(updateReqVo.id)
        validateUserNameUnique(updateReqVo.id, updateReqVo.username!!)
        validateUserPhoneUnique(updateReqVo.id, updateReqVo.phone!!)
        val user = convert(updateReqVo, User::class.java)
        user.password?.let {
            user.password = passwordEncoder.encode(it)
        }
        return updateById(user)
    }

    private fun validateUserNameUnique(id: Long?, username: String) =
        mapper.selectOneByCondition(User::username eq username)?.let { user ->
            if (user.id != id) throw exception(USER_NAME_DUPLICATE)
        }

    private fun validateUserPhoneUnique(id: Long?, phone: String) =
        mapper.selectOneByCondition(User::phone eq phone)?.let { user ->
            if (user.id != id) throw exception(USER_PHONE_DUPLICATE)
        }


    /**
     * 删除用户
     *
     * @param id 用户编号
     * @return 是否删除成功
     */
    override fun deleteUser(id: Long): Boolean {
        val user = validateUserExists(id)
        publishEvent(EntityDeleteEvent(user))
        return removeById(id)
    }


    /**
     * 校验用户是否存在
     *
     * @param id 用户编号
     */
    override fun validateUserExists(id: Long?): User = id?.let { getById(it) } ?: throw exception(USER_NOT_EXISTS)


    /**
     * 获得用户分页
     *
     * @param pageReqVO 分页查询
     * @return 用户分页
     */
    override fun getUserPage(pageReqVO: UserPageReqVO): PageResult<UserRespVO> = mapper.selectPage(pageReqVO)


    /**
     * 获得用户详情
     *
     * @param id 用户编号
     * @return 用户详情
     */
    override fun getUserById(id: Long): User = getById(id) ?: throw exception(USER_NOT_EXISTS)

    /**
     * 获得用户精简列表
     *
     * @return 用户列表
     */
    override fun getSimpleUserList(): List<UserSimpleRespVO> =
        mapper.selectListByConditionAs<UserSimpleRespVO> { User::status eq CommonStatusEnum.ENABLE }
}
