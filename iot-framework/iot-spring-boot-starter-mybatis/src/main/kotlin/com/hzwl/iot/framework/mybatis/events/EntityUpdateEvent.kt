package com.hzwl.iot.framework.mybatis.events

data class EntityUpdateEvent<E : Any>(override val entity: E) :
    EntityBaseEvent<E>(entity, entity.javaClass) {
    override fun toString(): String {
        return "EntityUpdateEvent<" + entityType.simpleName + ">" + this.entity
    }
}

