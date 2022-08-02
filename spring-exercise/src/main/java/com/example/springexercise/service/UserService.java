package com.example.springexercise.service;

import org.springframework.stereotype.Component;

/**
 * UserService.java
 * Description:
 *
 * @author Peng Shiquan
 * @date 2022/8/2
 */
public class UserService {

    private String text;

    public void test() {
        System.out.println("this is userService test method");
    }

    public void test2() {
        System.out.println("this is userService test2 method");
        System.out.println("text value is :" + text);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "UserService{" +
                "text='" + text + '\'' +
                '}';
    }
}
