package com.hzwl.iot.module.device.handler.product

import com.hzwl.iot.framework.mybatis.events.EntityUpdateEvent
import com.hzwl.iot.module.device.dal.entity.product.Product
import com.hzwl.iot.module.device.dal.entity.product.ProductCategory
import com.mybatisflex.kotlin.extensions.db.update
import com.mybatisflex.kotlin.extensions.kproperty.eq
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

/**
 * 产品事件处理器
 * @author lin
 */
@Component
class ProductEventHandler {

    @EventListener(condition = "#event.entityType == T(com.hzwl.iot.module.device.dal.entity.product.ProductCategory)")
    fun handleUpdateProductCategory(event: EntityUpdateEvent<ProductCategory>) {
        update {
            Product::categoryName set event.entity.name
            where(Product::categoryId eq event.entity.id)
        }
    }
}
