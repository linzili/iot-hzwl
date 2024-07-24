package com.hzwl.iot.framework.mybatis.config

import com.hzwl.iot.framework.mybatis.core.entity.BaseEntity
import com.hzwl.iot.framework.mybatis.core.handle.MyLogicDeleteProcessor
import com.hzwl.iot.framework.mybatis.core.handle.MyTenantFactory
import com.hzwl.iot.framework.mybatis.listener.CommentEventListener
import com.mybatisflex.core.FlexGlobalConfig
import com.mybatisflex.core.logicdelete.LogicDeleteProcessor
import com.mybatisflex.core.tenant.TenantFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * mybatis-flex 配置
 *
 */
@Configuration
class MyBatisFlexConfiguration {

    val config: FlexGlobalConfig = FlexGlobalConfig.getDefaultConfig()

    init {
//        AuditManager.setAuditEnable(true)

//        AuditManager.setMessageCollector { auditMessage ->
//            log.info("{},{}ms", auditMessage.fullSql, auditMessage.elapsedTime)
//        }

        setLogicDelete()

        setListener()

        setTenant()
    }

    private fun setTenant() {
        config.tenantColumn = "tenant_id"
    }

    private final fun setListener() {
        config.registerInsertListener(CommentEventListener(), BaseEntity::class.java)
        config.registerUpdateListener(CommentEventListener(), BaseEntity::class.java)
    }


    private final fun setLogicDelete() {
        config.logicDeleteColumn = "deleted"
    }

    @Bean
    fun logicDeleteProcessor(): LogicDeleteProcessor {
        return MyLogicDeleteProcessor()
    }

    @Bean
    fun tenantFactory(): TenantFactory {
        return MyTenantFactory()
    }
}


