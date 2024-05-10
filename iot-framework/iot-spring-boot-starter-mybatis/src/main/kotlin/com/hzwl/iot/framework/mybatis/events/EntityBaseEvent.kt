package com.hzwl.iot.framework.mybatis.events

import org.springframework.context.ApplicationEvent
import java.io.Serializable

open class EntityBaseEvent<E : Any>(open val entity: E, open val entityType: Class<E>) : ApplicationEvent(entityType),
    Serializable {
    override fun toString(): String {
        return "EntityEvent<" + entityType.simpleName + ">" + this.entity
    }

}
