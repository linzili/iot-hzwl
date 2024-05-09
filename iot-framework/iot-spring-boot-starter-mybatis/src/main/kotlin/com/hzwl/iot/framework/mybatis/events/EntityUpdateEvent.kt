package com.hzwl.iot.framework.mybatis.events

data class EntityUpdateEvent<E>(override val entity: E, override val entityType: Class<E>) :
    EntityBaseEvent<E>(entity, entityType) {
    override fun toString(): String {
        return "EntityUpdateEvent<" + entityType.simpleName + ">" + this.entity
    }
}

