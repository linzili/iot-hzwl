package com.hzwl.iot.module.device.dal.mapper.device

import com.hzwl.iot.module.device.dal.entity.device.Device
import com.mybatisflex.core.BaseMapper
import org.apache.ibatis.annotations.Mapper

@Mapper
interface DeviceMapper : BaseMapper<Device>
