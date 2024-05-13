package com.hzwl.iot.module.system.service.dict

import com.hzwl.iot.common.pojo.PageResult
import com.hzwl.iot.module.system.controller.dict.vo.data.DictDataPageReqVo
import com.hzwl.iot.module.system.controller.dict.vo.data.DictDataRespVO
import com.hzwl.iot.module.system.controller.dict.vo.data.DictDataSaveReqVo
import com.hzwl.iot.module.system.controller.dict.vo.data.DictDataSimpleRespVO
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

    /**
     * 修改字典数据
     *
     * @param updateReqVo 字典数据信息
     * @return Boolean 是否成功
     */
    fun updateDictData(updateReqVo: DictDataSaveReqVo): Boolean

    /**
     * 删除字典数据
     *
     * @param id 字典数据编号
     * @return Boolean 是否成功
     */
    fun deleteDictData(id: Long): Boolean

    /**
     * 获取全部字典数据列表
     *
     * @return 全部字典数据列表
     */
    fun getSimpleDictDataList(): List<DictDataSimpleRespVO>

    /**
     * 获得字典数据分页列表
     *
     * @param pageReqVo 分页参数
     */
    fun getDictDataPage(pageReqVo: DictDataPageReqVo): PageResult<DictDataRespVO>
}
