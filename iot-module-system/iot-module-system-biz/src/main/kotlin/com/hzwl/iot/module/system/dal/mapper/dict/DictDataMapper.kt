package com.hzwl.iot.module.system.dal.mapper.dict

import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.common.pojo.PageResult
import com.hzwl.iot.framework.mybatis.extensions.like
import com.hzwl.iot.framework.mybatis.extensions.paginateAs
import com.hzwl.iot.framework.mybatis.extensions.selectListByQueryAs
import com.hzwl.iot.module.system.controller.dict.vo.data.DictDataPageReqVo
import com.hzwl.iot.module.system.controller.dict.vo.data.DictDataRespVO
import com.hzwl.iot.module.system.controller.dict.vo.data.DictDataSimpleRespVO
import com.hzwl.iot.module.system.dal.entity.dict.DictData
import com.mybatisflex.core.BaseMapper
import com.mybatisflex.kotlin.extensions.condition.allAnd
import com.mybatisflex.kotlin.extensions.db.filterOne
import com.mybatisflex.kotlin.extensions.kproperty.eq
import com.mybatisflex.kotlin.extensions.kproperty.unaryPlus
import com.mybatisflex.kotlin.extensions.wrapper.andAll
import org.apache.ibatis.annotations.Mapper

@Mapper
@JvmDefaultWithCompatibility
interface DictDataMapper : BaseMapper<DictData> {

    /**
     * 根据字典类型和值查询字典数据
     *
     * @param dictType
     * @param value
     * @return
     */
    fun selectByDictTypeAndValue(dictType: String, value: String): DictData? {
        return filterOne(DictData::id) {
            allAnd(
                DictData::dictType eq dictType,
                DictData::value eq value
            )
        }
    }

    /**
     * 获得精简字典数据列表
     *
     * @return 精简字典数据列表
     */
    fun selectSimpleDictDataList(): List<DictDataSimpleRespVO> {
        return selectListByQueryAs<DictDataSimpleRespVO> {
            where(DictData::status eq CommonStatusEnum.ENABLE)
            orderBy(+DictData::dictType, +DictData::sort)
        }
    }

    /**
     * 分页查询字典数据
     *
     * @param pageReqVo
     * @return 字典数据
     */
    fun selectPage(pageReqVo: DictDataPageReqVo): PageResult<DictDataRespVO> =
        paginateAs<DictDataRespVO>(pageReqVo) {
            andAll(
                DictData::dictType eq pageReqVo.dictType,
                DictData::status eq pageReqVo.status,
                DictData::label like pageReqVo.label
            )
            orderBy(+DictData::sort)
        }


}
