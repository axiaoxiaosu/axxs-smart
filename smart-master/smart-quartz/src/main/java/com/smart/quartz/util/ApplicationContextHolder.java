package com.smart.quartz.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author lwq
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * 通过 beanName 获取bean
     */
    public static <T> T getBeanByName(String beanName) {
        return (T) applicationContext.getBean(beanName);
    }

    /**
     * 通过 Class 获取bean
     */
    public static <T> T getBeanByClass(Class<T> tClass) {
        return applicationContext.getBean(tClass);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHolder.applicationContext = applicationContext;
    }
}
