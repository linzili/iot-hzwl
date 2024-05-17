package com.hzwl.iot.module.device.service.device

import com.hzwl.iot.module.device.controller.device.vo.DeviceSaveReqVO
import com.hzwl.iot.module.device.dal.entity.device.Device
import com.mybatisflex.core.service.IService

interface DeviceService : IService<Device> {

    /**
     * 创建设备
     *
     * @param createReqVO 设备信息
     * @return 编号
     */
    fun createDevice(createReqVO: DeviceSaveReqVO): String

    /**
     * 修改设备
     *
     * @param updateReqVO 设备信息
     * @return 是否成功
     */
    fun updateDevice(updateReqVO: DeviceSaveReqVO): Boolean

    /**
     * 删除设备
     *
     * @param id 编号
     * @return 是否成功
     */
    fun deleteDevice(id: String): Boolean

}
