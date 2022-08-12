package com.psq.dubboserviceconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * DubboServiceConsumerApplication.java
 * Description:启动类
 *
 * @author Peng Shiquan
 * @date 2021/8/18
 */
@SpringBootApplication
@ImportResource("classpath:consumer.xml")
public class DubboServiceConsumerApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DubboServiceConsumerApplication.class, args);


    }
}
