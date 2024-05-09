package com.hzwl.iot.framework.mybatis.events

data class EntitySaveEvent<E>(override val entity: E, override val entityType: Class<E>) :
    EntityBaseEvent<E>(entity, entityType) {
    override fun toString(): String {
        return "EntityDeleteBeforeEvent<" + entityType.simpleName + ">" + this.entity
    }
}

