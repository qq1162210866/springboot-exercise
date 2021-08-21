package com.psq.dubbboserviceprovider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * DubboServiceProviderApplication.java
 * Description:
 *
 * @author Peng Shiquan
 * @date 2021/8/18
 */
@SpringBootApplication
public class DubboServiceProviderApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(DubboServiceProviderApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }

}
