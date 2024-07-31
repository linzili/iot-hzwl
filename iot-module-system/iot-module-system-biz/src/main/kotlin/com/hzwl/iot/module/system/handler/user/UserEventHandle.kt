package com.hzwl.iot.module.system.handler.user

import com.hzwl.iot.common.exception.util.ServiceExceptionUtil.exception
import com.hzwl.iot.framework.mybatis.events.EntityDeleteEvent
import com.hzwl.iot.module.system.dal.entity.dept.Dept
import com.hzwl.iot.module.system.dal.entity.users.User
import com.hzwl.iot.module.system.enums.ErrorCodeConstants.DEPT_EXISTS_USER
import com.mybatisflex.kotlin.extensions.kproperty.eq
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component


@Component
class UserEventHandle {

    @EventListener(condition = "#event.entityType == T(com.hzwl.iot.module.system.dal.entity.dept.Dept)")
    fun handleDeleteDept(event: EntityDeleteEvent<Dept>) {
        if (User.selectCountByCondition(User::deptId eq event.entity.id) > 0) {
            throw exception(DEPT_EXISTS_USER)
        }
    }
}
