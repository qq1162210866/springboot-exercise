package com.example.springexercise.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * ExerciseBeanPostProcessor.java
 * Description:
 *
 * @author Peng Shiquan
 * @date 2022/8/2
 */
@Component
public class ExerciseBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("orderService".equals(beanName)) System.err.println("初始化前OrderService");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("orderService".equals(beanName)) System.err.println("初始化后OrderService");
        return bean;
    }
}
