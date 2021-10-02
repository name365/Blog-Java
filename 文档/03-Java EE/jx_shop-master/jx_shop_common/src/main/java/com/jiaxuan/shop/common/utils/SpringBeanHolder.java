package com.jiaxuan.shop.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Author: jx
 * Date: 2019-03-12 23:37
 * Description: 获取bean工具类
 */
public class SpringBeanHolder implements ApplicationContextAware {

    private static ApplicationContext ac;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ac = applicationContext;
    }

    public static Object getBean(String beanName){
        return ac.getBean(beanName);
    }
}
