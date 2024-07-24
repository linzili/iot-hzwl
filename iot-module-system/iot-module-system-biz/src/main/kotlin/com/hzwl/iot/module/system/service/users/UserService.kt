package com.hzwl.iot.module.system.service.users

import com.hzwl.iot.common.pojo.PageResult
import com.hzwl.iot.module.system.controller.users.vo.UserPageReqVO
import com.hzwl.iot.module.system.controller.users.vo.UserRespVO
import com.hzwl.iot.module.system.controller.users.vo.UserSaveReqVO
import com.hzwl.iot.module.system.controller.users.vo.UserSimpleRespVO
import com.hzwl.iot.module.system.dal.entity.users.User
import com.mybatisflex.core.service.IService

interface UserService : IService<User> {

    /**
     * 创建用户
     *
     * @param createReqVo 创建信息
     * @return 用户编号
     */
    fun createUser(createReqVo: UserSaveReqVO): Long
    /**
     * 更新用户
     *
     * @param updateReqVo 更新信息
     * @return 是否更新成功
     */
    fun updateUser(updateReqVo: UserSaveReqVO): Boolean
    /**
     * 删除用户
     *
     * @param id 用户编号
     * @return 是否删除成功
     */
    fun deleteUser(id: Long): Boolean
    /**
     * 获得用户分页
     *
     * @param pageReqVO 分页查询
     * @return 用户分页
     */
    fun getUserPage(pageReqVO: UserPageReqVO): PageResult<UserRespVO>
    /**
     * 获得用户详情
     *
     * @param id 用户编号
     * @return 用户详情
     */
    fun getUserById(id: Long): User
    /**
     * 获得用户精简列表
     *
     * @return 用户列表
     */
    fun getSimpleUserList(): List<UserSimpleRespVO>

    /**
     * 校验用户是否存在
     *
     * @param id 用户编号
     */
    fun validateUserExists(id: Long?): User
}
