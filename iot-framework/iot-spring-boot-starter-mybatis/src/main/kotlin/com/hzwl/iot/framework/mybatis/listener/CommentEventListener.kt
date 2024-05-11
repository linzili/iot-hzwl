package com.hzwl.iot.framework.mybatis.listener

import cn.hutool.extra.spring.SpringUtil.publishEvent
import com.hzwl.iot.framework.mybatis.events.EntitySaveEvent
import com.hzwl.iot.framework.mybatis.events.EntityUpdateEvent
import com.mybatisflex.annotation.InsertListener
import com.mybatisflex.annotation.UpdateListener

/**
 * 更新数据时发布事件
 * @author lin
 */

class CommentEventListener : InsertListener, UpdateListener {

    /**
     * 更新操作的前置操作。
     *
     * @param entity 实体类
     */
    override fun onUpdate(entity: Any) {
        publishEvent(EntityUpdateEvent(entity))
    }

    /**
     * 新增操作的前置操作。
     *
     * @param entity 实体类
     */
    override fun onInsert(entity: Any) {
        publishEvent(EntitySaveEvent(entity))
    }

}
