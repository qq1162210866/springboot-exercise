package com.psq.dubbboserviceprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

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
