package com.hzwl.iot.module.system.service.dict

import com.hzwl.iot.common.pojo.PageResult
import com.hzwl.iot.module.system.controller.dict.vo.type.DictTypePageReqVO
import com.hzwl.iot.module.system.controller.dict.vo.type.DictTypeRespVO
import com.hzwl.iot.module.system.controller.dict.vo.type.DictTypeSaveReqVO
import com.hzwl.iot.module.system.controller.dict.vo.type.DictTypeSimpleRespVO
import com.hzwl.iot.module.system.dal.entity.dict.DictType
import com.mybatisflex.core.service.IService

interface DictTypeService : IService<DictType> {

    /**
     * 创建字典类型
     *
     * @param createReqVo 字典类型信息
     * @return 字典类型编号
     */
    fun createDictType(createReqVo: DictTypeSaveReqVO): Long

    /**
     * 修改字典类型
     *
     * @param updateReqVo 字典类型信息
     * @return 是否成功
     */
    fun updateDictType(updateReqVo: DictTypeSaveReqVO): Boolean

    /**
     * 删除字典类型
     *
     * @param id 字典类型编号
     * @return
     */
    fun deleteDictType(id: Long): Boolean

    /**
     * 获得字典类型分页列表
     *
     * @param pageReqVO 分页参数
     * @return
     */
    fun getDictTypePage(pageReqVO: DictTypePageReqVO): PageResult<DictTypeRespVO>

    /**
     * 获取全部字典类型精简列表
     *
     * @return
     */
    fun getSimpleDictTypeList(): List<DictTypeSimpleRespVO>
}
