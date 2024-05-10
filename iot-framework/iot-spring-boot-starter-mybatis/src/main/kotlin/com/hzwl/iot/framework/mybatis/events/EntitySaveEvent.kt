package com.hzwl.iot.framework.mybatis.events

data class EntitySaveEvent<E : Any>(override val entity: E) :
    EntityBaseEvent<E>(entity, entity.javaClass) {
    override fun toString(): String {
        return "EntitySaveEvent<" + entityType.simpleName + ">" + this.entity
    }
}

