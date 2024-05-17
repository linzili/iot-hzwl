package com.hzwl.iot.module.device.service.device

import cn.hutool.extra.spring.SpringUtil.publishEvent
import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.common.exception.util.ServiceExceptionUtil.exception
import com.hzwl.iot.common.extensions.convert
import com.hzwl.iot.module.device.controller.device.vo.DeviceSaveReqVO
import com.hzwl.iot.module.device.dal.entity.device.Device
import com.hzwl.iot.module.device.dal.mapper.device.DeviceMapper
import com.hzwl.iot.module.device.enums.ErrorCodeConstants
import com.hzwl.iot.module.device.service.product.ProductService
import com.mybatisflex.kotlin.extensions.kproperty.eq
import com.mybatisflex.spring.service.impl.ServiceImpl
import org.springframework.stereotype.Service

@Service
class DeviceServiceImpl(
    val productService: ProductService
) : ServiceImpl<DeviceMapper, Device>(), DeviceService {

    /**
     * 创建设备
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    override fun createDevice(createReqVO: DeviceSaveReqVO): String {
        createReqVO.id?.let { validateDeviceNotExist(it) }

        validateDeviceNameUnique(createReqVO.id, createReqVO.name!!)

        val device = convert(createReqVO, Device::class.java)

        device.productName = productService.validateProductExistAndPublish(createReqVO.productId!!).name

        save(device)

        return device.id!!
    }

    /**
     * 修改设备
     *
     * @param updateReqVO 设备信息
     * @return 是否成功
     */
    override fun updateDevice(updateReqVO: DeviceSaveReqVO): Boolean {
        validateDeviceExist(updateReqVO.id)

        validateDeviceNameUnique(updateReqVO.id, updateReqVO.name!!)

        val device = convert(updateReqVO, Device::class.java)

        device.productName = productService.validateProductExistAndPublish(updateReqVO.productId!!).name


        return updateById(device)
    }

    /**
     * 删除设备
     *
     * @param id 编号
     * @return 是否成功
     */
    override fun deleteDevice(id: String): Boolean {
        val device = validateDeviceExist(id)

        if (device.status == CommonStatusEnum.ENABLE) {
            throw exception(ErrorCodeConstants.DEVICE_ENABLE)
        }
        publishEvent(device)
        return removeById(id)
    }


    private fun validateDeviceExist(id: String?): Device =
        id?.let {
            return getById(id) ?: throw exception(ErrorCodeConstants.DEVICE_NOT_EXISTS)
        } ?: throw exception(ErrorCodeConstants.DEVICE_NOT_EXISTS)


    /**
     * 校验设备名称是否重复
     *
     * @param id
     * @param name
     */
    private fun validateDeviceNameUnique(id: String?, name: String) {
        val device = mapper.selectOneByCondition(Device::name eq name) ?: return

        if (id != null || device.id !== id) {
            throw exception(ErrorCodeConstants.DEVICE_NAME_DUPLICATE)
        }
    }

    /**
     * 校验设备不存在
     *
     * @param id
     */
    private fun validateDeviceNotExist(id: String) {
        if (exists(Device::id.eq(id))) {
            throw exception(ErrorCodeConstants.DEVICE_DUPLICATE)
        }
    }
}
