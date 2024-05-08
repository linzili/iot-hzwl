package com.hzwl.iot.module.system.service.dict

import com.hzwl.iot.module.system.controller.dict.vo.data.DictDataSaveReqVo
import com.hzwl.iot.module.system.dal.entity.dict.DictData
import com.mybatisflex.core.service.IService

/**
 * 字典数据 Service 接口
 *
 * @author lin
 */
interface DictDataService : IService<DictData> {
    /**
     * 保存字典数据
     *
     * @param createReqVo 字典数据信息
     * @return 字典数据编号
     */
    fun createDictData(createReqVo: DictDataSaveReqVo): Long
}
