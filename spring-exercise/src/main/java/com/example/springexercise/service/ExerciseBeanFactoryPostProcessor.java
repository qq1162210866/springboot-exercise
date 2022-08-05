package com.example.springexercise.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * ExerciseBeanFactoryPostProcessor.java
 * Description:
 *
 * @author Peng Shiquan
 * @date 2022/8/5
 */
@Component
public class ExerciseBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.err.println("开始初始化BeanFactory");
    }
}
