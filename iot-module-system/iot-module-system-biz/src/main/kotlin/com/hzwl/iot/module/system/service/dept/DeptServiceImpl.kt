package com.hzwl.iot.module.system.service.dept

import cn.hutool.extra.spring.SpringUtil.publishEvent
import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.common.exception.util.ServiceExceptionUtil.exception
import com.hzwl.iot.common.extensions.convert
import com.hzwl.iot.framework.mybatis.events.EntityDeleteEvent
import com.hzwl.iot.framework.mybatis.extensions.like
import com.hzwl.iot.framework.mybatis.extensions.selectListByConditionAs
import com.hzwl.iot.framework.mybatis.extensions.selectListWithRelationsByConditionAs
import com.hzwl.iot.module.system.controller.dept.vo.DeptListReqVO
import com.hzwl.iot.module.system.controller.dept.vo.DeptRespVO
import com.hzwl.iot.module.system.controller.dept.vo.DeptSaveReqVO
import com.hzwl.iot.module.system.controller.dept.vo.DeptSimpleRespVO
import com.hzwl.iot.module.system.dal.entity.dept.Dept
import com.hzwl.iot.module.system.dal.mapper.dept.DeptMapper
import com.hzwl.iot.module.system.enums.ErrorCodeConstants.DEPT_EXITS_CHILDREN
import com.hzwl.iot.module.system.enums.ErrorCodeConstants.DEPT_NAME_DUPLICATE
import com.hzwl.iot.module.system.enums.ErrorCodeConstants.DEPT_NOT_FOUND
import com.hzwl.iot.module.system.enums.ErrorCodeConstants.DEPT_PARENT_ERROR
import com.hzwl.iot.module.system.enums.ErrorCodeConstants.DEPT_PARENT_IS_CHILD
import com.hzwl.iot.module.system.enums.ErrorCodeConstants.DEPT_PARENT_NOT_EXITS
import com.mybatisflex.core.relation.RelationManager
import com.mybatisflex.kotlin.extensions.kproperty.eq
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.stereotype.Service

@Service
class DeptServiceImpl : ServiceImpl<DeptMapper, Dept>(), DeptService {
    /**
     * 创建部门
     *
     * @param createReqVo 创建部门信息
     * @return 部门编号
     */
    override fun createDept(createReqVo: DeptSaveReqVO): Long {

        validateParentDept(null, createReqVo.parentId)
        validateDeptNameUnique(null, createReqVo.parentId, createReqVo.name!!)

        return convert(createReqVo, Dept::class.java).run {
            if (this.parentId == null) {
                this.parentId = Dept.PARENT_ID_ROOT
            }
            save(this)
            this.id!!
        }
    }


    /**
     * 更新部门
     *
     * @param updateReqVo 更新部门信息
     * @return 是否成功
     */
    override fun updateDept(updateReqVo: DeptSaveReqVO): Boolean {
        validateDeptExists(updateReqVo.id)
        validateParentDept(updateReqVo.id, updateReqVo.parentId)
        validateDeptNameUnique(updateReqVo.id, updateReqVo.parentId, updateReqVo.name!!)
        return convert(updateReqVo, Dept::class.java).run {
            if (this.parentId == null) {
                this.parentId = Dept.PARENT_ID_ROOT
            }
            updateById(this)
        }
    }


    /**
     * 删除部门
     *
     * @param id 部门编号
     * @return 是否成功
     */
    override fun deleteDept(id: Long): Boolean {
        val dept = validateDeptExists(id)
        if (count(Dept::parentId eq id) > 0) {
            throw exception(DEPT_EXITS_CHILDREN)
        }
        publishEvent(EntityDeleteEvent(dept))
        return removeById(id)
    }

    /**
     * 获得部门列表
     *
     * @param reqVO 查询参数
     * @return 部门列表
     */
    override fun getDeptList(reqVO: DeptListReqVO): List<DeptRespVO> =
        mapper.selectListByConditionAs<DeptRespVO> {
            (Dept::name like reqVO.name)
                .and(Dept::status eq reqVO.status)
        }.apply { sortedBy { it.sort } }


    /**
     * 获得部门信息
     *
     * @param id 部门编号
     * @return 部门信息
     */
    override fun getDeptById(id: Long): Dept = validateDeptExists(id)


    /**
     * 获得部门精简列表
     */
    override fun getSimpleDeptList(): List<DeptSimpleRespVO> {
        return mapper.selectListByConditionAs<DeptSimpleRespVO> { Dept::status eq CommonStatusEnum.ENABLE }
    }

    /**
     * 获得部门树列表
     */
    override fun getDeptTreeList(reqVO: DeptListReqVO?): List<DeptRespVO> {
        RelationManager.setMaxDepth(32);
        return if (reqVO == null || (reqVO.name == null && reqVO.status == null))
            mapper.selectListWithRelationsByConditionAs<DeptRespVO>(Dept::parentId eq Dept.PARENT_ID_ROOT)
        else mapper.selectListWithRelationsByConditionAs<DeptRespVO>(
            Dept::name like reqVO.name,
            Dept::status eq reqVO.status
        )
    }


    /**
     * 获得部门精简信息列表
     *
     * @return 部门精简信息列表
     */
    override fun getSimpleDeptTreeList(): List<DeptSimpleRespVO> {
        RelationManager.setMaxDepth(32);
        return mapper.selectListWithRelationsByConditionAs<DeptSimpleRespVO>(
            Dept::parentId eq Dept.PARENT_ID_ROOT,
            Dept::status eq CommonStatusEnum.ENABLE
        )
    }


    private fun validateDeptExists(id: Long?): Dept =
        id?.let { mapper.selectOneByCondition(Dept::id eq id) } ?: throw exception(DEPT_NOT_FOUND)

    private fun validateDeptNameUnique(id: Long?, parentId: Long?, name: String) =
        mapper.selectByParentIdAndName(parentId, name)?.let {
            if (id == null || id != it.id) {
                throw exception(DEPT_NAME_DUPLICATE)
            }
        }

    private fun validateParentDept(id: Long?, parentId: Long?) {
        if (parentId == null || parentId == Dept.PARENT_ID_ROOT) return

        if (id == parentId) {
            throw exception(DEPT_PARENT_ERROR)
        }

        var parentDept = getById(parentId) ?: throw exception(DEPT_PARENT_NOT_EXITS)

        if (id == null) return
        while (true) {
            if (parentDept.parentId == id) {
                throw exception(DEPT_PARENT_IS_CHILD)
            }
            if (parentDept.parentId == null || parentDept.parentId == Dept.PARENT_ID_ROOT) {
                break
            }
            parentDept = getById(parentDept.parentId) ?: throw exception(DEPT_PARENT_NOT_EXITS)
        }
    }
}
