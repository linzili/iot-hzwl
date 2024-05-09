package com.hzwl.iot.module.system.service.dict

import com.hzwl.iot.common.exception.util.ServiceExceptionUtil.exception
import com.hzwl.iot.common.extensions.convert
import com.hzwl.iot.common.pojo.PageResult
import com.hzwl.iot.module.system.controller.dict.vo.data.DictDataPageReqVo
import com.hzwl.iot.module.system.controller.dict.vo.data.DictDataRespVO
import com.hzwl.iot.module.system.controller.dict.vo.data.DictDataSaveReqVo
import com.hzwl.iot.module.system.controller.dict.vo.data.DictDataSimpleRespVO
import com.hzwl.iot.module.system.dal.entity.dict.DictData
import com.hzwl.iot.module.system.dal.mapper.dict.DictDataMapper
import com.hzwl.iot.module.system.enums.ErrorCodeConstants.DICT_DATA_NOT_EXISTS
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

        val dictData = convert(createReqVo, DictData::class.java)
        save(dictData)
        return dictData.id!!
    }

    /**
     * 修改字典数据
     *
     * @param updateReqVo 字典数据信息
     */
    override fun updateDictData(updateReqVo: DictDataSaveReqVo): Boolean {

        validateDictDataExists(updateReqVo.id)

        validateDictTypeExists(updateReqVo.dictType!!)

        validateDictDataValueUnique(updateReqVo.id, updateReqVo.dictType, updateReqVo.value!!)

        val dictData = convert(updateReqVo, DictData::class.java)

        return updateById(dictData)
    }

    /**
     * 删除字典数据
     *
     * @param id 字典数据编号
     * @return Boolean 是否成功
     */
    override fun deleteDictData(id: Long): Boolean {

        validateDictDataExists(id)

        return removeById(id)
    }

    /**
     * 获取全部字典数据列表
     *
     * @return 全部字典数据列表
     */
    override fun getSimpleDictDataList(): List<DictDataSimpleRespVO> {
        return mapper.selectSimpleDictDataList()
    }

    /**
     * 获得字典数据分页列表
     *
     * @param pageReqVo 分页参数
     */
    override fun getDictDataPage(pageReqVo: DictDataPageReqVo): PageResult<DictDataRespVO> {
        return mapper.selectPage(pageReqVo)
    }

    /**
     * 校验字典数据是否存在
     *
     * @param id
     */
    private fun validateDictDataExists(id: Long?) {
        if (id == null) throw exception(DICT_DATA_NOT_EXISTS)
        getById(id) ?: throw exception(DICT_DATA_NOT_EXISTS)
    }

    /**
     * 校验字典类型是否有效
     *
     * @param dictType 字典数据类型
     */
    fun validateDictTypeExists(dictType: String) {

    }

    /**
     * 校验字典数据值的唯一性
     *
     * @param id 字典数据编号
     * @param dictType 字典数据类型
     * @param value 字典值
     */
    fun validateDictDataValueUnique(id: Long?, dictType: String, value: String) {
        val dictData = mapper.selectByDictTypeAndValue(dictType, value) ?: return
        if (id == null || id != dictData.id) {
            throw exception(DICT_DATA_VALUE_DUPLICATE)
        }
    }

}
