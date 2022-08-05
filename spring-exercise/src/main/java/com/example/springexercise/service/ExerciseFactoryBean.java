package com.example.springexercise.service;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * ExerciseFactoryBean.java
 * Description:
 *
 * @author Peng Shiquan
 * @date 2022/8/2
 */
@Component
public class ExerciseFactoryBean implements FactoryBean {

    @Override
    public Object getObject() throws Exception {
        UserService userService = new UserService();
        userService.setText("this is ExerciseFactoryBean");
        return userService;
    }

    @Override
    public Class<?> getObjectType() {
        return UserService.class;
    }
}
