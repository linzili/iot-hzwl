package com.hzwl.iot.module.system.service.dept

import com.hzwl.iot.module.system.controller.dept.vo.DeptListReqVO
import com.hzwl.iot.module.system.controller.dept.vo.DeptRespVO
import com.hzwl.iot.module.system.controller.dept.vo.DeptSaveReqVO
import com.hzwl.iot.module.system.controller.dept.vo.DeptSimpleRespVO
import com.hzwl.iot.module.system.dal.entity.dept.Dept
import com.mybatisflex.core.service.IService

interface DeptService : IService<Dept> {

    /**
     * 创建部门
     *
     * @param createReqVo 创建部门信息
     * @return 部门编号
     */
    fun createDept(createReqVo: DeptSaveReqVO): Long

    /**
     * 更新部门
     *
     * @param updateReqVo 更新部门信息
     * @return 是否成功
     */
    fun updateDept(updateReqVo: DeptSaveReqVO): Boolean

    /**
     * 删除部门
     *
     * @param id 部门编号
     * @return 是否成功
     */
    fun deleteDept(id: Long): Boolean

    /**
     * 获得部门列表
     *
     * @param reqVO 查询参数
     * @return 部门列表
     */
    fun getDeptList(reqVO: DeptListReqVO): List<DeptRespVO>

    /**
     * 获得部门信息
     *
     * @param id 部门编号
     * @return 部门信息
     */
    fun getDeptById(id: Long): Dept

    /**
     * 获得部门精简列表
     */
    fun getSimpleDeptList(): List<DeptSimpleRespVO>


    /**
     * 获得部门树列表
     *
     * @return 部门树列表
     */
    fun getDeptTreeList(reqVO: DeptListReqVO?): List<DeptRespVO>

    /**
     * 获得部门精简信息列表
     *
     * @return 部门精简信息列表
     */
    fun getSimpleDeptTreeList(): List<DeptSimpleRespVO>
}
