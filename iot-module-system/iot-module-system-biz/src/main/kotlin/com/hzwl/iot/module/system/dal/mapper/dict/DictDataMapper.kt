package com.hzwl.iot.module.system.dal.mapper.dict

import com.hzwl.iot.module.system.dal.entity.dict.DictData
import com.mybatisflex.core.BaseMapper
import org.apache.ibatis.annotations.Mapper

@Mapper
interface DictDataMapper : BaseMapper<DictData> {
}
