package com.hzwl.iot.framework.mybatis.listener

import com.hzwl.iot.common.utils.SpringContextUtil
import com.hzwl.iot.framework.mybatis.events.EntityUpdateEvent
import com.mybatisflex.annotation.UpdateListener

/**
 * 更新数据时发布事件
 * @author lin
 */

class UpdateEventListener : UpdateListener {

    /**
     * 更新操作的前置操作。
     *
     * @param entity 实体类
     */
    override fun onUpdate(entity: Any) {
        SpringContextUtil.eventPublisher.publishEvent(EntityUpdateEvent(entity, entity.javaClass))
    }
}
