package com.hzwl.iot.module.system.service.dict

import com.hzwl.iot.common.exception.util.ServiceExceptionUtil.exception
import com.hzwl.iot.common.extensions.convert
import com.hzwl.iot.module.system.controller.dict.vo.type.DictTypeSaveReqVO
import com.hzwl.iot.module.system.dal.entity.dict.DictType
import com.hzwl.iot.module.system.dal.mapper.dict.DictTypeMapper
import com.hzwl.iot.module.system.enums.ErrorCodeConstants
import com.mybatisflex.kotlin.extensions.kproperty.eq
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.stereotype.Service

@Service
class DictTypeServiceImpl : ServiceImpl<DictTypeMapper, DictType>(), DictTypeService {
    /**
     * 创建字典类型
     *
     * @param createReqVo 字典类型信息
     * @return 字典类型编号
     */
    override fun createDictType(createReqVo: DictTypeSaveReqVO): Long {

        validateDictTypeNameUnique(null, createReqVo.name!!)

        validateDictTypeUnique(null, createReqVo.type!!)
        val dictType = convert(createReqVo, DictType::class.java)
        save(dictType)
        return dictType.id!!
    }

    /**
     * 校验字典类型是否唯一
     *
     * @param id 字典类型编号
     * @param type 字典类型
     */
    private fun validateDictTypeUnique(id: Long?, type: String) {
        val dictType = mapper.selectOneByCondition(DictType::type eq type) ?: return
        if (dictType.id != id) {
            throw exception(ErrorCodeConstants.DICT_TYPE_TYPE_DUPLICATE)
        }
    }

    /**
     * 校验字典类型名称唯一
     *
     * @param id 字典类型编号
     * @param name 字典类型名称
     */
    private fun validateDictTypeNameUnique(id: Long?, name: String) {
        val dictType = mapper.selectOneByCondition(DictType::name eq name) ?: return
        if (dictType.id != id) {
            throw exception(ErrorCodeConstants.DICT_TYPE_NAME_DUPLICATE)
        }
    }
}