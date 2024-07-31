package com.hzwl.iot.module.system.handler.dept

import com.hzwl.iot.common.exception.util.ServiceExceptionUtil.exception
import com.hzwl.iot.framework.mybatis.events.EntityDeleteEvent
import com.hzwl.iot.module.system.dal.entity.dept.Dept
import com.hzwl.iot.module.system.dal.entity.users.User
import com.hzwl.iot.module.system.enums.ErrorCodeConstants.USER_IS_DEPT_LEADER
import com.mybatisflex.kotlin.extensions.kproperty.eq
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class DeptEventhandle {


    @EventListener(condition = "#event.entityType == T(com.hzwl.iot.module.system.dal.entity.users.User)")
    fun handleDeleteUser(event: EntityDeleteEvent<User>) {
        if(Dept.selectCountByCondition(Dept::leaderUserId eq event.entity.id)>0) {
            throw exception(USER_IS_DEPT_LEADER)
        }
    }
}
