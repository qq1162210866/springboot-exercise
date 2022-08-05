package com.example.springexercise.bean;

import org.springframework.core.Ordered;

/**
 * B.java
 * Description:
 *
 * @author Peng Shiquan
 * @date 2022/8/5
 */
public class B implements Ordered {

    @Override
    public int getOrder() {
        return 2;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
