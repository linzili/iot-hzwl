package com.hzwl.iot.module.system.service.dict

import cn.hutool.extra.spring.SpringUtil.publishEvent
import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.common.exception.util.ServiceExceptionUtil.exception
import com.hzwl.iot.common.extensions.convert
import com.hzwl.iot.common.pojo.PageResult
import com.hzwl.iot.framework.mybatis.events.EntityDeleteEvent
import com.hzwl.iot.framework.mybatis.extensions.selectListByQueryAs
import com.hzwl.iot.module.system.controller.dict.vo.type.DictTypePageReqVO
import com.hzwl.iot.module.system.controller.dict.vo.type.DictTypeRespVO
import com.hzwl.iot.module.system.controller.dict.vo.type.DictTypeSaveReqVO
import com.hzwl.iot.module.system.controller.dict.vo.type.DictTypeSimpleRespVO
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
        dictType.id = null
        save(dictType)
        return dictType.id!!
    }

    /**
     * 修改字典类型
     *
     * @param updateReqVo 字典类型信息
     * @return 是否成功
     */
    override fun updateDictType(updateReqVo: DictTypeSaveReqVO): Boolean {
        validateDictTypeExists(updateReqVo.id)
        validateDictTypeNameUnique(updateReqVo.id, updateReqVo.name!!)
        validateDictTypeUnique(updateReqVo.id, updateReqVo.type!!)

        val dictType = convert(updateReqVo, DictType::class.java)
        return updateById(dictType)
    }

    /**
     * 删除字典类型
     *
     * @param id 字典类型编号
     * @return
     */
    override fun deleteDictType(id: Long): Boolean {
        val dictType = validateDictTypeExists(id)

        publishEvent(EntityDeleteEvent(dictType))

        return removeById(id)
    }

    /**
     * 获得字典类型分页列表
     *
     * @param pageReqVO 分页参数
     * @return
     */
    override fun getDictTypePage(pageReqVO: DictTypePageReqVO): PageResult<DictTypeRespVO> =
        mapper.selectPage(pageReqVO)


    /**
     * 获取全部字典类型精简列表
     *
     * @return
     */
    override fun getSimpleDictTypeList(): List<DictTypeSimpleRespVO> {
        return mapper.selectListByQueryAs<DictTypeSimpleRespVO> {
            where(DictType::status eq CommonStatusEnum.ENABLE)
        }
    }


    /**
     * 校验字典类型是否存在
     *
     * @param id 字典类型编号
     */
    private fun validateDictTypeExists(id: Long?): DictType =
        id?.let {
            return getById(it) ?: throw exception(ErrorCodeConstants.DICT_TYPE_NOT_EXISTS)
        } ?: throw exception(ErrorCodeConstants.DICT_TYPE_NOT_EXISTS)

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
