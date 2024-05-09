package com.hzwl.iot.common.utils

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.ApplicationEventPublisherAware
import org.springframework.stereotype.Component

/**
 * SpringContext工具类
 * @author lin
 */
@Component
class SpringContextUtil : ApplicationContextAware, ApplicationEventPublisherAware {
    /**
     * Set the ApplicationContext that this object runs in.
     * Normally this call will be used to initialize the object.
     *
     * Invoked after population of normal bean properties but before an init callback such
     * as [org.springframework.beans.factory.InitializingBean.afterPropertiesSet]
     * or a custom init-method. Invoked after [ResourceLoaderAware.setResourceLoader],
     * [ApplicationEventPublisherAware.setApplicationEventPublisher] and
     * [MessageSourceAware], if applicable.
     * @param applicationContext the ApplicationContext object to be used by this object
     * @throws ApplicationContextException in case of context initialization errors
     * @throws BeansException if thrown by application context methods
     * @see org.springframework.beans.factory.BeanInitializationException
     */

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        context = applicationContext
    }


    /**
     * Set the ApplicationEventPublisher that this object runs in.
     *
     * Invoked after population of normal bean properties but before an init
     * callback like InitializingBean's afterPropertiesSet or a custom init-method.
     * Invoked before ApplicationContextAware's setApplicationContext.
     * @param applicationEventPublisher event publisher to be used by this object
     */
    override fun setApplicationEventPublisher(applicationEventPublisher: ApplicationEventPublisher) {
        eventPublisher = applicationEventPublisher
    }

    companion object {
        lateinit var context: ApplicationContext
        lateinit var eventPublisher: ApplicationEventPublisher
    }
}
