package com.example.springexercise.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * OrderService.java
 * Description:
 *
 * @author Peng Shiquan
 * @date 2022/8/2
 */
@Component
public class OrderService {

    @Value("1")
    private UserService userService;

    @Override
    public String toString() {
        return "OrderService{" +
                "userService=" + userService +
                '}';
    }
}
