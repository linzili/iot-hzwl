package com.hzwl.iot.module.system.dal.mapper.dict

import com.hzwl.iot.module.system.dal.entity.dict.DictData
import com.mybatisflex.core.BaseMapper
import com.mybatisflex.kotlin.extensions.db.filterOne
import com.mybatisflex.kotlin.extensions.kproperty.eq
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
            (DictData::dictType eq dictType)
                .and(DictData::value eq value)
        }
    }
}
