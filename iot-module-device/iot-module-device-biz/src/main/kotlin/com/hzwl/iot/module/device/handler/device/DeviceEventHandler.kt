package com.hzwl.iot.module.device.handler.device

import com.hzwl.iot.common.enums.CommonStatusEnum
import com.hzwl.iot.common.exception.util.ServiceExceptionUtil.exception
import com.hzwl.iot.framework.mybatis.events.EntityDeleteEvent
import com.hzwl.iot.framework.mybatis.events.EntityUpdateEvent
import com.hzwl.iot.module.device.dal.entity.device.Device
import com.hzwl.iot.module.device.dal.entity.product.Product
import com.hzwl.iot.module.device.enums.ErrorCodeConstants
import com.hzwl.iot.module.device.service.device.DeviceService
import com.mybatisflex.kotlin.extensions.kproperty.eq
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

/**
 * 设备事件处理器
 * @author lin
 */
@Component
class DeviceEventHandler(
    val deviceService: DeviceService
) {


    /**
     * 处理产品更新事件
     *
     * @param event
     */
    @EventListener(condition = "#event.entityType == T(com.hzwl.iot.module.device.dal.entity.product.Product)")
    fun handleUpdateProduct(event: EntityUpdateEvent<Product>) {
        if (event.entity.publish === CommonStatusEnum.DISABLE) {
            validateProductHasNoDevices(event.entity.id!!)
        }
    }


    /**
     * 处理产品删除事件
     *
     * @param event
     */
    @EventListener(condition = "#event.entityType == T(com.hzwl.iot.module.device.dal.entity.product.Product)")
    fun handleDeleteProduct(event: EntityDeleteEvent<Product>) = validateProductHasNoDevices(event.entity.id!!)

    private fun validateProductHasNoDevices(productId: Long) {
        if (Device.selectCountByCondition(Device::productId eq productId) > 0) {
            throw exception(ErrorCodeConstants.PRODUCT_EXISTS_DEVICE)
        }
    }
}
