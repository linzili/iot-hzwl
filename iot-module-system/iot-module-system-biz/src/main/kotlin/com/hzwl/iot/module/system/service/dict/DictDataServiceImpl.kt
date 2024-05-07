package com.hzwl.iot.module.system.service.dict

import com.hzwl.iot.module.system.dal.entity.dict.DictData
import com.hzwl.iot.module.system.dal.mapper.dict.DictDataMapper
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.stereotype.Service

@Service
class DictDataServiceImpl : ServiceImpl<DictDataMapper, DictData>(), DictDataService {

}
