package com.hzwl.iot.module.system.service.dict

import com.hzwl.iot.common.exception.ServiceException
import com.hzwl.iot.common.extensions.convert
import com.hzwl.iot.module.system.controller.dict.vo.data.DictDataSaveReqVo
import com.hzwl.iot.module.system.dal.entity.dict.DictData
import com.hzwl.iot.module.system.dal.mapper.dict.DictDataMapper
import com.hzwl.iot.module.system.enums.ErrorCodeConstants.DICT_DATA_VALUE_DUPLICATE
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.stereotype.Service

@Service
class DictDataServiceImpl : ServiceImpl<DictDataMapper, DictData>(), DictDataService {
    /**
     * 保存字典数据
     *
     * @param createReqVo 字典数据信息
     * @return 字典数据编号
     */
    override fun createDictData(createReqVo: DictDataSaveReqVo): Long {

        validateDictTypeExists(createReqVo.dictType!!)

        validateDictDataValueUnique(null, createReqVo.dictType, createReqVo.value!!)

        val dictData = convert.convert(createReqVo, DictData::class.java)
        save(dictData)
        return dictData.id!!
    }

    /**
     * 校验字典类型是否有效
     *
     * @param type
     */
    fun validateDictTypeExists(type: String) {

    }

    /**
     * 校验字典数据值的唯一性
     *
     * @param id
     * @param dictType
     * @param value
     */
    fun validateDictDataValueUnique(id: Long?, dictType: String, value: String) {
        val dictData = mapper.selectByDictTypeAndValue(dictType, value) ?: return
        if (id == null || !id.equals(dictData.id)) {
            throw ServiceException(DICT_DATA_VALUE_DUPLICATE)
        }
    }

}
