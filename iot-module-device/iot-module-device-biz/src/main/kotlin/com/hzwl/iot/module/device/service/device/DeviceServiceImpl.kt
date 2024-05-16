package com.hzwl.iot.module.device.service.device

import com.hzwl.iot.module.device.dal.entity.device.Device
import com.hzwl.iot.module.device.dal.mapper.device.DeviceMapper
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.stereotype.Service

@Service
class DeviceServiceImpl : ServiceImpl<DeviceMapper, Device>(), DeviceService
