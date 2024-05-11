package com.hzwl.iot.module.system.handler.dict

import com.hzwl.iot.common.exception.util.ServiceExceptionUtil.exception
import com.hzwl.iot.framework.mybatis.events.EntityDeleteEvent
import com.hzwl.iot.framework.mybatis.events.EntityUpdateEvent
import com.hzwl.iot.framework.mybatis.extensions.selectOneByQueryAs
import com.hzwl.iot.framework.mybatis.extensions.update
import com.hzwl.iot.module.system.dal.entity.dict.DictData
import com.hzwl.iot.module.system.dal.entity.dict.DictType
import com.hzwl.iot.module.system.enums.ErrorCodeConstants
import com.mybatisflex.kotlin.extensions.kproperty.eq
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

/**
 * 字典数据事件处理器
 * @author lin
 */
@Component
class DictDataEventHandle {

    /**
     * 删除字典类型时校验是否存在字典数据
     *
     * @param event
     */
    @EventListener(condition = "#event.entityType == T(com.hzwl.iot.module.system.dal.entity.dict.DictType)")
    fun handleDeleteDictType(event: EntityDeleteEvent<DictType>) {
        if (DictData.selectCountByCondition(DictData::dictType eq event.entity.type) > 0) {
            throw exception(ErrorCodeConstants.DICT_TYPE_HAS_CHILDREN)
        }
    }

    /**
     * 更新字典类型时更新字典数据的类型
     *
     * @param event
     */

    @EventListener(condition = "#event.entityType == T(com.hzwl.iot.module.system.dal.entity.dict.DictType)")
    fun handleUpdateDictType(event: EntityUpdateEvent<DictType>) {
        val dictType = DictType.selectOneByQueryAs<String> {
            select(DictType::type)
            where(DictType::id eq event.entity.id)
        } ?: return

        DictData.update {
            DictData::dictType set event.entity.type
            where(DictData::dictType eq dictType)
        }
    }
}
