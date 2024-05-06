package com.hzwl.config

import com.hzwl.extensions.log
import com.mybatisflex.core.audit.AuditManager
import org.springframework.context.annotation.Configuration

@Configuration
class MyBatisFlexConfiguration() {
    init {
        AuditManager.setAuditEnable(true)

        AuditManager.setMessageCollector { auditMessage ->
            log.info("{},{}ms", auditMessage.fullSql, auditMessage.elapsedTime)
        }
    }
}


