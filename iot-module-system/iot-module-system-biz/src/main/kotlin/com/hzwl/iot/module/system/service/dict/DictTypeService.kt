package com.hzwl.iot.module.system.service.dict

import com.hzwl.iot.module.system.controller.dict.vo.type.DictTypeSaveReqVO
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
}
