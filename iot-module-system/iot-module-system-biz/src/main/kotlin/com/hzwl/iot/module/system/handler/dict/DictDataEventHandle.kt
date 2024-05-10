package com.hzwl.iot.module.system.handler.dict

import com.hzwl.iot.common.exception.util.ServiceExceptionUtil.exception
import com.hzwl.iot.framework.mybatis.events.EntityDeleteEvent
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
    @EventListener
    fun handleDeleteDictType(event: EntityDeleteEvent<DictType>) {
        if (DictData.selectCountByCondition(DictData::dictType eq event.entity.type) > 0) {
            throw exception(ErrorCodeConstants.DICT_TYPE_HAS_CHILDREN)
        }
    }
}
