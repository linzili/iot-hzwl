package com.hzwl.iot.module.system.dal.mapper.dict

import com.hzwl.iot.common.pojo.PageResult
import com.hzwl.iot.framework.mybatis.extensions.between
import com.hzwl.iot.framework.mybatis.extensions.like
import com.hzwl.iot.framework.mybatis.extensions.paginateAs
import com.hzwl.iot.module.system.controller.dict.vo.type.DictTypePageReqVO
import com.hzwl.iot.module.system.controller.dict.vo.type.DictTypeRespVO
import com.hzwl.iot.module.system.dal.entity.dict.DictType
import com.mybatisflex.core.BaseMapper
import com.mybatisflex.kotlin.extensions.kproperty.eq
import com.mybatisflex.kotlin.extensions.kproperty.unaryPlus
import com.mybatisflex.kotlin.extensions.wrapper.andAll
import org.apache.ibatis.annotations.Mapper

@Mapper
@JvmDefaultWithCompatibility
interface DictTypeMapper : BaseMapper<DictType> {
    fun selectPage(pageReqVO: DictTypePageReqVO): PageResult<DictTypeRespVO> {
        return paginateAs<DictTypeRespVO>(pageReqVO) {
            andAll(
                DictType::name like pageReqVO.name,
                DictType::type like pageReqVO.type,
                DictType::status eq pageReqVO.status,
                DictType::createTime between pageReqVO.createTime,
            )
            orderBy(+DictType::id)
        }
    }
}


