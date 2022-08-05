package com.example.springexercise.bean;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.Annotation;

/**
 * A.java
 * Description:
 *
 * @author Peng Shiquan
 * @date 2022/8/5
 */
public class A implements Ordered {
    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
