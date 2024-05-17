package com.hzwl.iot.module.device.dal.mapper.device

import com.hzwl.iot.common.pojo.PageResult
import com.hzwl.iot.framework.mybatis.extensions.between
import com.hzwl.iot.framework.mybatis.extensions.like
import com.hzwl.iot.framework.mybatis.extensions.paginateAs
import com.hzwl.iot.module.device.controller.device.vo.DevicePageReqVO
import com.hzwl.iot.module.device.controller.device.vo.DeviceRespVO
import com.hzwl.iot.module.device.dal.entity.device.Device
import com.mybatisflex.core.BaseMapper
import com.mybatisflex.kotlin.extensions.kproperty.eq
import com.mybatisflex.kotlin.extensions.kproperty.unaryPlus
import com.mybatisflex.kotlin.extensions.wrapper.andAll
import org.apache.ibatis.annotations.Mapper

@Mapper
interface DeviceMapper : BaseMapper<Device> {
    fun selectPage(pageReqVO: DevicePageReqVO): PageResult<DeviceRespVO> =
        paginateAs<DeviceRespVO>(pageReqVO) {
            andAll(
                Device::name like pageReqVO.name,
                Device::productId eq pageReqVO.productId,
                Device::status eq pageReqVO.status,
                Device::createTime between pageReqVO.createTime
            )
            orderBy(+Device::id)
        }
}
