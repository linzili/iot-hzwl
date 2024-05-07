package com.hzwl.framework.mybatis.config

import com.hzwl.extensions.log
import com.hzwl.framework.mybatis.core.handle.MyLogicDeleteProcessor
import com.mybatisflex.core.FlexGlobalConfig
import com.mybatisflex.core.audit.AuditManager
import com.mybatisflex.core.logicdelete.LogicDeleteProcessor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * mybatis-flex 配置
 *
 */
@Configuration
class MyBatisFlexConfiguration() {
    init {
        AuditManager.setAuditEnable(true)

        AuditManager.setMessageCollector { auditMessage ->
            log.info("{},{}ms", auditMessage.fullSql, auditMessage.elapsedTime)
        }

        setLogicDelete()
    }


    private final fun setLogicDelete() {
        val config = FlexGlobalConfig.getDefaultConfig()
        config.logicDeleteColumn = "deleted"
    }

    @Bean
    fun logicDeleteProcessor(): LogicDeleteProcessor {
        return MyLogicDeleteProcessor()
    }
}


